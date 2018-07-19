package com.bigtiger.kromise

import com.bigtiger.kromise.impl.DeferredObject
import java.util.concurrent.Callable

abstract class DeferredCallable<D>: Callable<D> {
    private val deferred = DeferredObject<D, Throwable>()
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


    fun getDeferred(): Deferred<D, Throwable> {
        return deferred
    }

    fun getStartPolicy(): StartPolicy {
        return startPolicy
    }
}