package com.bigtiger.kromise.impl

import com.bigtiger.kromise.DoneFilter
import com.bigtiger.kromise.FailFilter
import com.bigtiger.kromise.Kromise
import com.bigtiger.kromise.ProgressFilter
import com.bigtiger.kromise.ProgressCallback
import com.bigtiger.kromise.FailCallback
import com.bigtiger.kromise.DoneCallback

class FilteredKromise<D, F, P, D_OUT, F_OUT, P_OUT>(promise: Kromise<D, F, P>,
                                                    val doneFilter: DoneFilter<in D, out D_OUT> = object : DoneFilter<D, D_OUT>{
                                                        override fun filterDone(result: D): D_OUT {
                                                            return result as D_OUT
                                                        }
                                                    },
                                                    val failFilter: FailFilter<in F, out F_OUT> = object :FailFilter<F, F_OUT>{
                                                        override fun filterFail(result: F): F_OUT {
                                                            return result as F_OUT
                                                        }
                                                    },
                                                    val progressFilter: ProgressFilter<in P, out P_OUT> = object : ProgressFilter<P, P_OUT>{
                                                        override fun filterProgress(progress: P): P_OUT {
                                                            return progress as P_OUT
                                                        }
                                                    })
    :DeferredObject<D_OUT, F_OUT, P_OUT>(), Kromise<D_OUT, F_OUT, P_OUT>{

    init {
        promise.done(object : DoneCallback<D> {
            override fun onDone(result: D?) {
                if (result != null) {
                    this@FilteredKromise.resolve(this@FilteredKromise.doneFilter.filterDone(result))
                }
            }
        }).fail(object : FailCallback<F> {
            override fun onFail(result: F?) {
                if (result != null) {
                    this@FilteredKromise.reject(this@FilteredKromise.failFilter.filterFail(result))
                }
            }
        }).progress(object : ProgressCallback<P> {

            override fun onProgress(progress: P) {
                this@FilteredKromise.notify(this@FilteredKromise.progressFilter.filterProgress(progress))
            }
        })
    }
}

class NoOpFailFilter<F> : FailFilter<F, F> {
    override fun filterFail(result: F): F {
        return result
    }
}

class NoOpProgressFilter<P> : ProgressFilter<P, P> {
    override fun filterProgress(progress: P): P {
        return progress
    }
}