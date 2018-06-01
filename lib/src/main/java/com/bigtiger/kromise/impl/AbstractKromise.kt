package com.bigtiger.kromise.impl

import com.bigtiger.kromise.*
import java.util.concurrent.CopyOnWriteArrayList


abstract class AbstractKromise<D, F,P> : Kromise<D, F, P> {
//    protected val log = LoggerFactory.getLogger(AbstractKromise::class.java)

    @Volatile
    protected var state = State.PENDING

    protected val doneCallbacks: MutableList<DoneCallback<in D>> = CopyOnWriteArrayList<DoneCallback<in D>>()
    protected val failCallbacks: MutableList<FailCallback<in F>> = CopyOnWriteArrayList<FailCallback<in F>>()
    protected val progressCallbacks: MutableList<ProgressCallback<in P>> = CopyOnWriteArrayList<ProgressCallback<in P>>()
    protected val alwaysCallbacks: MutableList<AlwaysCallback<in D, in F>> = CopyOnWriteArrayList<AlwaysCallback<in D, in F>>()

    protected var resolveResult: D? = null
    protected var rejectResult: F? = null
    val lock = Object()

    override fun state(): State {
        return state
    }

    override fun done(callback: DoneCallback<in D>): Kromise<D, F, P> {
        synchronized(this) {
            if (isResolved()) {
                triggerDone(callback, resolveResult)
            } else {
                doneCallbacks.add(callback)
            }
        }
        return this
    }

    override fun fail(callback: FailCallback<in F>): Kromise<D, F, P> {
        synchronized(this) {
            if (isRejected()) {
                triggerFail(callback, rejectResult)
            } else {
                failCallbacks.add(callback)
            }
        }
        return this
    }

    override fun always(callback: AlwaysCallback<in D, in F>): Kromise<D, F, P> {
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
    }

    protected fun triggerDone(callback: DoneCallback<in D>, resolved: D?) {
        try {
            callback.onDone(resolved)
        } catch (e: Exception) {
            handleException(CallbackType.DONE_CALLBACK, e)
        }

    }

    protected fun triggerFail(rejected: F) {
        for (callback in failCallbacks) {
            triggerFail(callback, rejected)
        }
        failCallbacks.clear()
    }

    protected fun triggerFail(callback: FailCallback<in F>, rejected: F?) {
        try {
            callback.onFail(rejected)
        } catch (e: Exception) {
            handleException(CallbackType.FAIL_CALLBACK, e)
        }

    }

    protected fun triggerProgress(progress: P) {
        for (callback in progressCallbacks) {
            triggerProgress(callback, progress)
        }
    }

    protected fun triggerProgress(callback: ProgressCallback<in P>, progress: P) {
        try {
            callback.onProgress(progress)
        } catch (e: Exception) {
            handleException(CallbackType.PROGRESS_CALLBACK, e)
        }

    }

    protected fun triggerAlways(state: State, resolve: D?, reject: F?) {
        for (callback in alwaysCallbacks) {
            triggerAlways(callback, state, resolve, reject)
        }
        alwaysCallbacks.clear()
//        val lock = Object()
        synchronized(lock) {
            lock.notifyAll()
            //this@AbstractKromise.notifyAll()
        }
    }

    protected fun triggerAlways(callback: AlwaysCallback<in D, in F>, state: State, resolve: D?, reject: F?) {
        try {
            callback.onAlways(state, resolve, reject)
        } catch (e: Exception) {
            handleException(CallbackType.ALWAYS_CALLBACK, e)
        }

    }

    override fun progress(callback: ProgressCallback<in P>): Kromise<D, F, P> {
        progressCallbacks.add(callback)
        return this
    }

    override fun then(callback: DoneCallback<in D>): Kromise<D, F, P> {
        return done(callback)
    }

    override fun then(doneCallback: DoneCallback<in D>, failCallback: FailCallback<in F>): Kromise<D, F, P> {
        done(doneCallback)
        fail(failCallback)
        return this
    }

    override fun then(doneCallback: DoneCallback<in D>, failCallback: FailCallback<in F>,
                      progressCallback: ProgressCallback<in P>): Kromise<D, F, P> {
        done(doneCallback)
        fail(failCallback)
        progress(progressCallback)
        return this
    }

    override fun <D_OUT> then(
            doneFilter: DoneFilter<in D, out D_OUT>): Kromise<D_OUT, F, P> {
        return FilteredKromise<D, F, P, D_OUT, F, P>(this, doneFilter)
    }

    override fun <D_OUT, F_OUT> then(
            doneFilter: DoneFilter<in D, out D_OUT>, failFilter: FailFilter<in F, out F_OUT>): Kromise<D_OUT, F_OUT, P> {
        return FilteredKromise<D, F, P, D_OUT, F_OUT, P>(this, doneFilter, failFilter)
    }

    override fun <D_OUT, F_OUT, P_OUT> then(
            doneFilter: DoneFilter<in D, out D_OUT>, failFilter: FailFilter<in F, out F_OUT>,
            progressFilter: ProgressFilter<in P, out P_OUT>): Kromise<D_OUT, F_OUT, P_OUT> {
        return FilteredKromise<D, F, P, D_OUT, F_OUT, P_OUT>(this, doneFilter, failFilter, progressFilter)
    }

    override fun <D_OUT> then(
            doneFilter: DonePipe<in D, out D_OUT, out F, out P>): Kromise<D_OUT, F, P> {
        return PipedKromise<D, F, P, D_OUT, F, P>(this, doneFilter, null, null)
    }

    override fun <D_OUT, F_OUT> then(
            doneFilter: DonePipe<in D, out D_OUT, out F_OUT, out P>,
            failFilter: FailPipe<in F, out D_OUT, out F_OUT, out P>): Kromise<D_OUT, F_OUT, P> {
        return PipedKromise<D, F, P, D_OUT, F_OUT, P>(this, doneFilter, failFilter, null)
    }

    override fun <D_OUT, F_OUT, P_OUT> then(
            doneFilter: DonePipe<in D, out D_OUT, out F_OUT, out P_OUT>,
            failFilter: FailPipe<in F, out D_OUT, out F_OUT, out P_OUT>,
            progressFilter: ProgressPipe<in P, out D_OUT, out F_OUT, out P_OUT>): Kromise<D_OUT, F_OUT, P_OUT> {
        return PipedKromise<D, F, P, D_OUT, F_OUT, P_OUT>(this, doneFilter, failFilter, progressFilter)
    }

    override fun <D_OUT, F_OUT> always(alwaysFilter: AlwaysPipe<in D, in F, out D_OUT, out F_OUT, out P>): Kromise<D_OUT, F_OUT, P> {
        return PipedKromise<D, F, P, D_OUT, F_OUT, P>(this, alwaysFilter)
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
                        //this@AbstractKromise.wait()
                    } else {
                        val elapsed = System.currentTimeMillis() - startTime
                        val waitTime = timeout - elapsed
                        lock.wait(waitTime)
                        //this@AbstractKromise.wait(waitTime)
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