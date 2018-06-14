package com.bigtiger.kromise.impl

import com.bigtiger.kromise.*

class TransformerKromise<D, F, P, D_OUT, F_OUT, P_OUT>(promise: Kromise<D, F, P>,
                                                       val doneFilter: (D)->D_OUT = {
                                                           it as D_OUT
                                                       },
                                                       val failFilter: (F)-> F_OUT = {
                                                           it as F_OUT
                                                       },
                                                       val progressFilter: (P)->P_OUT ={
                                                           it as P_OUT
                                                       })
    :DeferredObject<D_OUT, F_OUT, P_OUT>(){

    init {
        promise.done(object : DoneCallback<D> {
            override fun onDone(result: D?) {
                if (result != null) {
                    this@TransformerKromise.resolve(this@TransformerKromise.doneFilter(result))
                }
            }
        }).fail(object : FailCallback<F> {
            override fun onFail(result: F?) {
                if (result != null) {
                    this@TransformerKromise.reject(this@TransformerKromise.failFilter(result))
                }
            }
        }).progress(object : ProgressCallback<P> {

            override fun onProgress(progress: P) {
                this@TransformerKromise.notify(this@TransformerKromise.progressFilter(progress))
            }
        })
    }
}