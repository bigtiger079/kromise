package com.bigtiger.kromise

import com.bigtiger.kromise.impl.DeferredObject
import java.util.concurrent.Callable



abstract class DeferredCallable<D, P>: Callable<D> {
    private val deferred = DeferredObject<D, Throwable, P>()
    private val startPolicy: StartPolicy

    /**
     * Creates a new `DeferredCallable` with DEFAULT `startPolicy`.
     */
    constructor() {
        this.startPolicy = StartPolicy.DEFAULT
    }

    /**
     * Creates a new `DeferredCallable` with the given `startPolicy`.
     *
     * @param startPolicy the startPolicy to use. will be set to DEFAULT if `null`.
     */
    constructor(startPolicy: StartPolicy?) {
        this.startPolicy = startPolicy ?: StartPolicy.DEFAULT
    }

    /**
     * Trigger notification of an intermediate result.
     *
     * @param progress the value to be sent as a notification
     *
     * @see Deferred.notify
     */
    protected fun notify(progress: P) {
        deferred.notify(progress)
    }

    fun getDeferred(): Deferred<D, Throwable, P> {
        return deferred
    }

    fun getStartPolicy(): StartPolicy {
        return startPolicy
    }
}