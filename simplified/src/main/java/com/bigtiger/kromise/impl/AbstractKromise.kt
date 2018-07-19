package com.bigtiger.kromise.impl

import com.bigtiger.kromise.*
import java.util.concurrent.CopyOnWriteArrayList


abstract class AbstractKromise<D, F> : Kromise<D, F> {

    @Volatile
    protected var state = State.PENDING

    protected val dones: MutableList<(D?)->Unit> = CopyOnWriteArrayList<(D?)->Unit>()
    protected val fails: MutableList<(F?)->Unit> = CopyOnWriteArrayList<(F?)->Unit>()
    protected val alwaysList: MutableList<(State, D?, F?)->Unit> = CopyOnWriteArrayList()

    protected val doneCallbacks: MutableList<DoneCallback<in D>> = CopyOnWriteArrayList<DoneCallback<in D>>()
    protected val failCallbacks: MutableList<FailCallback<in F>> = CopyOnWriteArrayList<FailCallback<in F>>()
    protected val alwaysCallbacks: MutableList<AlwaysCallback<in D, in F>> = CopyOnWriteArrayList<AlwaysCallback<in D, in F>>()

    protected var resolveResult: D? = null
    protected var rejectResult: F? = null
    val lock = Object()

    override fun state(): State {
        return state
    }

    override fun done(callback: DoneCallback<in D>): Kromise<D, F> {
        synchronized(this) {
            if (isResolved()) {
                triggerDone(callback, resolveResult)
            } else {
                doneCallbacks.add(callback)
            }
        }
        return this
    }

    override fun done(callback: (D?) -> Unit): Kromise<D, F> {
        synchronized(this) {
            if (isResolved()) {
                triggerDone(callback, resolveResult)
            } else {
                dones.add(callback)
            }
        }
        return this
    }

    override fun fail(callback: FailCallback<in F>): Kromise<D, F> {
        synchronized(this) {
            if (isRejected()) {
                triggerFail(callback, rejectResult)
            } else {
                failCallbacks.add(callback)
            }
        }
        return this
    }

    override fun always(callback: AlwaysCallback<in D, in F>): Kromise<D, F> {
        synchronized(this) {
            if (isPending()) {
                alwaysCallbacks.add(callback)
            } else {
                triggerAlways(callback, state, resolveResult, rejectResult)
            }
        }
        return this
    }

    protected fun triggerDone(resolved: D) {
        for (callback in doneCallbacks) {
            triggerDone(callback, resolved)
        }
        doneCallbacks.clear()
        for (done in dones) {
            done(resolved)
        }
        dones.clear()
    }

    protected fun triggerDone(callback: DoneCallback<in D>, resolved: D?) {
        try {
            callback.onDone(resolved)
        } catch (e: Exception) {
            handleException(CallbackType.DONE_CALLBACK, e)
        }

    }

    protected fun triggerDone(callback: (D?)->Unit, resolve: D?) {
        try {
            callback(resolve)
        } catch (e: Exception) {
            handleException(CallbackType.DONE_CALLBACK, e)
        }
    }

    protected fun triggerFail(rejected: F) {
        for (callback in failCallbacks) {
            triggerFail(callback, rejected)
        }
        failCallbacks.clear()
        for (fail in fails) {
            triggerFail(fail, rejected)
        }
        fails.clear()
    }

    protected fun triggerFail(callback: (F?)->Unit, rejected: F?) {
        try {
            callback(rejected)
        } catch (e: Exception) {
            handleException(CallbackType.FAIL_CALLBACK, e)
        }
    }

    protected fun triggerFail(callback: FailCallback<in F>, rejected: F?) {
        try {
            callback.onFail(rejected)
        } catch (e: Exception) {
            handleException(CallbackType.FAIL_CALLBACK, e)
        }
    }



    protected fun triggerAlways(state: State, resolve: D?, reject: F?) {
        for (callback in alwaysCallbacks) {
            triggerAlways(callback, state, resolve, reject)
        }
        alwaysCallbacks.clear()

        for (always in alwaysList) {
            triggerAlways(always, state, resolve, reject)
        }
        alwaysList.clear()
        synchronized(lock) {
            lock.notifyAll()
        }
    }

    protected fun triggerAlways(callback: AlwaysCallback<in D, in F>, state: State, resolve: D?, reject: F?) {
        try {
            callback.onAlways(state, resolve, reject)
        } catch (e: Exception) {
            handleException(CallbackType.ALWAYS_CALLBACK, e)
        }
    }

    protected fun triggerAlways(callback: (State, D?, F?)->Unit, state: State, resolve: D?, reject: F?) {
        try {
            callback(state, resolve, reject)
        } catch (e: Exception) {
            handleException(CallbackType.ALWAYS_CALLBACK, e)
        }
    }


    override fun then(doneCallback: DoneCallback<in D>): Kromise<D, F> {
        return done(doneCallback)
    }


    override fun then(doneCallback: DoneCallback<in D>, failCallback: FailCallback<in F>): Kromise<D, F> {
        done(doneCallback)
        fail(failCallback)
        return this
    }


    override fun <D_OUT> then(doneFilter: DoneFilter<in D, out D_OUT>): Kromise<D_OUT, F> {
        return FilteredKromise(this, doneFilter)
    }

    override fun <D_OUT, F_OUT> then(doneFilter: DoneFilter<in D, out D_OUT>,
                                     failFilter: FailFilter<in F, out F_OUT>): Kromise<D_OUT, F_OUT> {

        return FilteredKromise(this, doneFilter, failFilter)
    }


    override fun <D_OUT> then(donePipe: DonePipe<in D, out D_OUT, out F>): Kromise<D_OUT, F> {

        return PipedKromise(this, donePipe, null)
    }

    override fun <D_OUT, F_OUT> then(donePipe: DonePipe<in D, out D_OUT, out F_OUT>,
                                     failPipe: FailPipe<in F, out D_OUT, out F_OUT>): Kromise<D_OUT, F_OUT> {

        return PipedKromise(this, donePipe, failPipe)
    }


    override fun <D_OUT> then(doneFilter: (D) -> D_OUT): Kromise<D_OUT, F> {
        return TransformerKromise(this, doneFilter)
    }

    override fun <D_OUT, F_OUT> then(doneFilter: (D) -> D_OUT,
                                     failFilter: (F) -> F_OUT): Kromise<D_OUT, F_OUT> {

        return TransformerKromise(this, doneFilter, failFilter)
    }

    override fun <D_OUT, F_OUT> always(alwaysPipe: AlwaysPipe<in D, in F, out D_OUT, out F_OUT>): Kromise<D_OUT, F_OUT> {
        return PipedKromise<D, F, D_OUT, F_OUT>(this, alwaysPipe)
    }

    override fun isPending(): Boolean {
        return state === State.PENDING
    }

    override fun isResolved(): Boolean {
        return state === State.RESOLVED
    }

    override fun isRejected(): Boolean {
        return state === State.REJECTED
    }

    @Throws(InterruptedException::class)
    override fun waitSafely() {
        waitSafely(-1)
    }

    @Throws(InterruptedException::class)
    override fun waitSafely(timeout: Long) {
        val startTime = System.currentTimeMillis()
        synchronized(lock) {
            while (this.isPending()) {
                try {
                    if (timeout <= 0) {
                        lock.wait()
                    } else {
                        val elapsed = System.currentTimeMillis() - startTime
                        val waitTime = timeout - elapsed
                        lock.wait(waitTime)
                    }
                } catch (e: InterruptedException) {
                    Thread.currentThread().interrupt()
                    throw e
                }

                if (timeout > 0 && System.currentTimeMillis() - startTime >= timeout) {
                    return
                } else {
                    continue // keep looping
                }
            }
        }
    }

    protected fun handleException(callbackType: CallbackType, e: Exception) {
        GlobalConfiguration.getGlobalCallbackExceptionHandler().handleException(callbackType, e)
    }
}