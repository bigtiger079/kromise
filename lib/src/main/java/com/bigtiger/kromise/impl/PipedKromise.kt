package com.bigtiger.kromise.impl

import com.bigtiger.kromise.*
import com.bigtiger.kromise.ProgressCallback
import com.bigtiger.kromise.AlwaysCallback

class PipedKromise<D, F, P, D_OUT, F_OUT, P_OUT> ()
    : DeferredObject<D_OUT, F_OUT, P_OUT>(), Kromise<D_OUT, F_OUT, P_OUT> {

    constructor(kromise: Kromise<D, F, P>,
                doneFilter: DonePipe<in D, out D_OUT, out F_OUT, out P_OUT>?,
                failFilter: FailPipe<in F, out D_OUT, out F_OUT, out P_OUT>?,
                progressFilter: ProgressPipe<in P, out D_OUT, out F_OUT, out P_OUT>?):this() {

        kromise.done(object : DoneCallback<D> {
            override fun onDone(result: D?) {
                if (doneFilter != null)
                    pipe(doneFilter.pipeDone(result!!))
                else
                    this@PipedKromise.resolve(result as D_OUT)

            }
        }).fail(object : FailCallback<F> {
            override fun onFail(result: F?) {
                if (failFilter != null)
                    pipe(failFilter.pipeFail(result!!))
                else
                    this@PipedKromise.reject(result as F_OUT)
            }
        }).progress(object : ProgressCallback<P> {
            override fun onProgress(progress: P) {
                if (progressFilter != null)
                    pipe(progressFilter.pipeProgress(progress))
                else
                    this@PipedKromise.notify(progress as P_OUT)
            }
        })
    }

    constructor(kromise: Kromise<D, F, P_OUT>,
                alwaysFilter: AlwaysPipe<in D, in F, out D_OUT, out F_OUT, out P_OUT>): this() {
        kromise.always(object : AlwaysCallback<D, F> {
            override fun onAlways(state: State, resolved: D?, rejected: F?) {
                pipe(alwaysFilter.pipeAlways(state, resolved!!, rejected!!))
            }
        }).progress(object : ProgressCallback<P_OUT> {
            override fun onProgress(progress: P_OUT) {
                this@PipedKromise.notify(progress)
            }
        })
    }

    protected fun pipe(kromise: Kromise<out D_OUT, out F_OUT, out P_OUT>): Kromise<out D_OUT, out F_OUT, out P_OUT> {
        kromise.done(object : DoneCallback<D_OUT> {
            override fun onDone(result: D_OUT?) {
                this@PipedKromise.resolve(result!!)
            }
        }).fail(object : FailCallback<F_OUT> {
            override fun onFail(result: F_OUT?) {
                this@PipedKromise.reject(result!!)
            }
        }).progress(object : ProgressCallback<P_OUT> {
            override fun onProgress(progress: P_OUT) {
                this@PipedKromise.notify(progress)
            }
        })

        return kromise
    }
}