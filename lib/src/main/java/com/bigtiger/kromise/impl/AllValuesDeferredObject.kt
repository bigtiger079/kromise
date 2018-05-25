package com.bigtiger.kromise.impl

import com.bigtiger.kromise.DoneCallback
import com.bigtiger.kromise.FailCallback
import com.bigtiger.kromise.Kromise
import com.bigtiger.kromise.ProgressCallback
import com.bigtiger.kromise.multiple.*
import java.util.concurrent.atomic.AtomicInteger


class AllValuesDeferredObject(kromises:Array<Kromise<*, *, *>>)
    : DeferredObject<AllValues, Throwable, MasterProgress>()
        , Kromise<AllValues, Throwable, MasterProgress> {

    private val values: MutableAllValues
    private val numberOfKromise: Int = kromises.size
    private val failCount: AtomicInteger = AtomicInteger()
    private val doneCount: AtomicInteger = AtomicInteger()

    init {
        values = DefaultMutableAllValues(numberOfKromise)
        for (i in 0 until numberOfKromise) {
            configureKromise(i, kromises[i])
        }
    }

    fun <D, F, P>configureKromise(index: Int, kromise: Kromise<D, F, P>) {
        kromise.fail(object : FailCallback<F>{
            override fun onFail(result: F) {
                synchronized(this@AllValuesDeferredObject) {
                    if (!this@AllValuesDeferredObject.isPending()) {
                        return
                    }
                    values.set(index, OneReject(index, kromise, result))
                    val fail = failCount.incrementAndGet()
                    val done = doneCount.incrementAndGet()
                    this@AllValuesDeferredObject.notify(MasterProgress(doneCount.get(), fail, numberOfKromise))
                    if (fail+done == numberOfKromise) {
                        this@AllValuesDeferredObject.resolve(values)
                    }
                }
            }
        }).progress(object : ProgressCallback<P>{
            override fun onProgress(progress: P) {
                synchronized(this@AllValuesDeferredObject) {
                    if (!this@AllValuesDeferredObject.isPending())
                        return

                    this@AllValuesDeferredObject.notify(OneProgress<P>(
                            doneCount.get(),
                            failCount.get(),
                            numberOfKromise, index, kromise, progress))
                }
            }
        }).done(object : DoneCallback<D>{
            override fun onDone(result: D) {
                synchronized(this@AllValuesDeferredObject) {
                    if (!this@AllValuesDeferredObject.isPending())
                        return

                    values.set(index, OneResult<D>(index, kromise, result))
                    val fail = failCount.get()
                    val done = doneCount.incrementAndGet()

                    this@AllValuesDeferredObject.notify(MasterProgress(
                            done,
                            failCount.get(),
                            numberOfKromise))

                    if (fail + done == numberOfKromise) {
                        this@AllValuesDeferredObject.resolve(values)
                    }
                }
            }
        })
    }
}