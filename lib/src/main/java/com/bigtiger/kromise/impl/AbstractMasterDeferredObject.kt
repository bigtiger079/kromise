package com.bigtiger.kromise.impl

import com.bigtiger.kromise.DoneCallback
import com.bigtiger.kromise.ProgressCallback
import com.bigtiger.kromise.FailCallback
import com.bigtiger.kromise.Kromise
import com.bigtiger.kromise.multiple.*
import java.util.concurrent.atomic.AtomicInteger


open class AbstractMasterDeferredObject(private val results: MutableMultipleResults): DeferredObject<MultipleResults, OneReject<*>, MasterProgress>() {
    private val numberOfPromises: Int = results.size()
    private val doneCount = AtomicInteger()
    private val failCount = AtomicInteger()


    protected fun <D, F, P> configureKromise(index: Int, promise: Kromise<D, F, P>) {
        promise.fail(object : FailCallback<F> {
            override fun onFail(result: F?) {
                synchronized(this@AbstractMasterDeferredObject) {
                    if (!this@AbstractMasterDeferredObject.isPending())
                        return

                    val fail = failCount.incrementAndGet()
                    this@AbstractMasterDeferredObject.notify(MasterProgress(
                            doneCount.get(),
                            fail,
                            numberOfPromises))

                    this@AbstractMasterDeferredObject.reject(OneReject(index, promise, result))
                }
            }
        }).progress(object : ProgressCallback<P> {
            override fun onProgress(progress: P) {
                synchronized(this@AbstractMasterDeferredObject) {
                    if (!this@AbstractMasterDeferredObject.isPending())
                        return

                    this@AbstractMasterDeferredObject.notify(OneProgress<P>(
                            doneCount.get(),
                            failCount.get(),
                            numberOfPromises, index, promise, progress))
                }
            }
        }).done(object : DoneCallback<D> {
            override fun onDone(result: D) {
                synchronized(this@AbstractMasterDeferredObject) {
                    if (!this@AbstractMasterDeferredObject.isPending())
                        return

                    results.set(index, OneResult(index, promise, result))
                    val done = doneCount.incrementAndGet()

                    this@AbstractMasterDeferredObject.notify(MasterProgress(
                            done,
                            failCount.get(),
                            numberOfPromises))

                    if (done == numberOfPromises) {
                        this@AbstractMasterDeferredObject.resolve(results)
                    }
                }
            }
        })
    }
}