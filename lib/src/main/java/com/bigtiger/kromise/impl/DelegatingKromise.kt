package com.bigtiger.kromise.impl

import com.bigtiger.kromise.Kromise
import com.bigtiger.kromise.ProgressCallback
import com.bigtiger.kromise.AlwaysCallback
import com.bigtiger.kromise.FailCallback
import com.bigtiger.kromise.DoneCallback
import com.bigtiger.kromise.AlwaysPipe
import com.bigtiger.kromise.ProgressPipe
import com.bigtiger.kromise.FailPipe
import com.bigtiger.kromise.DonePipe
import com.bigtiger.kromise.ProgressFilter
import com.bigtiger.kromise.FailFilter
import com.bigtiger.kromise.DoneFilter
import com.bigtiger.kromise.State


abstract class DelegatingKromise<D, F, P>(val delegate: Kromise<D, F, P>): Kromise<D, F, P> {
    

    override fun state(): State {
        return delegate.state()
    }

    override fun isPending(): Boolean {
        return delegate.isPending()
    }

    override fun isResolved(): Boolean {
        return delegate.isResolved()
    }

    override fun isRejected(): Boolean {
        return delegate.isRejected()
    }

    override fun then(doneCallback: DoneCallback<in D>): Kromise<D, F, P> {
        return delegate.then(doneCallback)
    }

    override fun then(doneCallback: DoneCallback<in D>, failCallback: FailCallback<in F>): Kromise<D, F, P> {
        return delegate.then(doneCallback, failCallback)
    }

    override fun then(doneCallback: DoneCallback<in D>,
                      failCallback: FailCallback<in F>,
                      progressCallback: ProgressCallback<in P>): Kromise<D, F, P> {
        return delegate.then(doneCallback, failCallback, progressCallback)
    }

    override fun <D_OUT> then(doneFilter: DoneFilter<in D, out D_OUT>): Kromise<D_OUT, F, P> {
        return delegate.then(doneFilter)
    }

    override fun <D_OUT, F_OUT> then(doneFilter: DoneFilter<in D, out D_OUT>,
                                     failFilter: FailFilter<in F, out F_OUT>): Kromise<D_OUT, F_OUT, P> {
        return delegate.then(doneFilter, failFilter)
    }

    override fun <D_OUT, F_OUT, P_OUT> then(doneFilter: DoneFilter<in D, out D_OUT>,
                                            failFilter: FailFilter<in F, out F_OUT>,
                                            progressFilter: ProgressFilter<in P, out P_OUT>): Kromise<D_OUT, F_OUT, P_OUT> {
        return delegate.then(doneFilter, failFilter, progressFilter)
    }

    override fun <D_OUT> then(donePipe: DonePipe<in D, out D_OUT, out F, out P>): Kromise<D_OUT, F, P> {
        return delegate.then(donePipe)
    }

    override fun <D_OUT, F_OUT> then(donePipe: DonePipe<in D, out D_OUT, out F_OUT, out P>,
                                     failPipe: FailPipe<in F, out D_OUT, out F_OUT, out P>): Kromise<D_OUT, F_OUT, P> {
        return delegate.then(donePipe, failPipe)
    }

    override fun <D_OUT, F_OUT, P_OUT> then(donePipe: DonePipe<in D, out D_OUT, out F_OUT, out P_OUT>,
                                            failPipe: FailPipe<in F, out D_OUT, out F_OUT, out P_OUT>,
                                            progressPipe: ProgressPipe<in P, out D_OUT, out F_OUT, out P_OUT>): Kromise<D_OUT, F_OUT, P_OUT> {
        return delegate.then(donePipe, failPipe, progressPipe)
    }

    override fun <D_OUT, F_OUT> always(alwaysPipe: AlwaysPipe<in D, in F, out D_OUT, out F_OUT, out P>): Kromise<D_OUT, F_OUT, P> {
        return delegate.always(alwaysPipe)
    }

    override fun done(callback: DoneCallback<in D>): Kromise<D, F, P> {
        return delegate.done(callback)
    }

    override fun fail(callback: FailCallback<in F>): Kromise<D, F, P> {
        return delegate.fail(callback)
    }

    override fun always(callback: AlwaysCallback<in D, in F>): Kromise<D, F, P> {
        return delegate.always(callback)
    }

    override fun progress(callback: ProgressCallback<in P>): Kromise<D, F, P> {
        return delegate.progress(callback)
    }

    @Throws(InterruptedException::class)
    override fun waitSafely() {
        delegate.waitSafely()
    }

    @Throws(InterruptedException::class)
    override fun waitSafely(timeout: Long) {
        delegate.waitSafely(timeout)
    }
}