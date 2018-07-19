package com.bigtiger.kromise

import com.bigtiger.kromise.impl.DeferredObject

abstract class DeferredRunnable(private val startPolicy: StartPolicy = StartPolicy.DEFAULT): Runnable {
    private val deferred: Deferred<Void, Throwable> = DeferredObject()

    fun getDeferred() = deferred

    fun getStartPolicy() = startPolicy
}