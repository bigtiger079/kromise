package com.bigtiger.kromise.impl

import com.bigtiger.kromise.DoneCallback
import com.bigtiger.kromise.FailCallback
import com.bigtiger.kromise.Kromise
import com.bigtiger.kromise.multiple.*
import java.util.concurrent.atomic.AtomicInteger


open class AbstractMasterDeferredObject(private val results: MutableMultipleResults): DeferredObject<MultipleResults, OneReject<*>>() {
    private val numberOfPromises: Int = results.size()
    private val doneCount = AtomicInteger()
    private val failCount = AtomicInteger()

    protected fun <D, F> configureKromise(index: Int, promise: Kromise<D, F>) {
        promise.fail(object : FailCallback<F> {
            override fun onFail(result: F?) {
                synchronized(this@AbstractMasterDeferredObject) {
                    if (!this@AbstractMasterDeferredObject.isPending())
                        return

                    val fail = failCount.incrementAndGet()
                    this@AbstractMasterDeferredObject.reject(OneReject(index, promise, result!!))
                }
            }
        }).done(object : DoneCallback<D> {
            override fun onDone(result: D?) {
                synchronized(this@AbstractMasterDeferredObject) {
                    if (!this@AbstractMasterDeferredObject.isPending())
                        return

                    results.set(index, OneResult(index, promise, result!!))
                    val done = doneCount.incrementAndGet()

                    if (done == numberOfPromises) {
                        this@AbstractMasterDeferredObject.resolve(results)
                    }
                }
            }
        })
    }
}