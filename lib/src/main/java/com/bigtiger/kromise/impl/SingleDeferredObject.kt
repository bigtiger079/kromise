package com.bigtiger.kromise.impl

import com.bigtiger.kromise.Kromise
import com.bigtiger.kromise.multiple.OneResult
import com.bigtiger.kromise.DoneCallback
import com.bigtiger.kromise.multiple.OneReject
import com.bigtiger.kromise.FailCallback
import com.bigtiger.kromise.DeferredFutureTask


internal class SingleDeferredObject(tasks: Array<DeferredFutureTask<*, *>?>) : DeferredObject<OneResult<*>, OneReject<Throwable>, Void>(), Kromise<OneResult<*>, OneReject<Throwable>, Void> {
    private var resolvedOrRejectedTaskIndex: Int = 0

    init {
        for (index in tasks.indices) {
            configureTask(index, tasks[index]!!)
        }

        fail(object : FailCallback<OneReject<Throwable>> {
            override fun onFail(result: OneReject<Throwable>?) {
                cancelAllTasks(tasks)
            }
        })

        done(object : DoneCallback<OneResult<*>> {
            override fun onDone(result: OneResult<*>?) {
                cancelAllTasks(tasks)
            }
        })
    }

    private fun cancelAllTasks(tasks: Array<DeferredFutureTask<*, *>?>) {
        for (index in tasks.indices) {
            val task = tasks[index]
            if (index != resolvedOrRejectedTaskIndex) {
                task?.cancel(true)
            }
        }
    }

    private fun <D, P> configureTask(index: Int, task: DeferredFutureTask<D, P>) {
        task.kromise().fail(object : FailCallback<Throwable> {
            override fun onFail(reject: Throwable?) {
                synchronized(this@SingleDeferredObject) {
                    if (this@SingleDeferredObject.isPending()) {
                        // task $index is rejected
                        resolvedOrRejectedTaskIndex = index
                        this@SingleDeferredObject.reject(OneReject(index, task.kromise(), reject!!))
                    }
                }
            }
        }).done(object : DoneCallback<D> {
            override fun onDone(result: D?) {
                synchronized(this@SingleDeferredObject) {
                    if (this@SingleDeferredObject.isPending()) {
                        // task $index is resolved
                        resolvedOrRejectedTaskIndex = index
                        this@SingleDeferredObject.resolve(OneResult(index, task.kromise(), result!!))
                    }
                }
            }
        })
    }
}