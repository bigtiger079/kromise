package com.bigtiger.kromise

import com.bigtiger.kromise.impl.DeferredObject

abstract class DeferredRunnable<P>(private val startPolicy: StartPolicy = StartPolicy.DEFAULT): Runnable {
    private val deferred: Deferred<Void, Throwable, P> = DeferredObject()

    protected fun notify(progress: P) {
        deferred.notify(progress)
    }

    fun getDeferred() = deferred

    fun getStartPolicy() = startPolicy
}