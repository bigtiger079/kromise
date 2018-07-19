package com.bigtiger.kromise.impl

import com.bigtiger.kromise.Kromise
import com.bigtiger.kromise.AlwaysCallback
import com.bigtiger.kromise.FailCallback
import com.bigtiger.kromise.DoneCallback
import com.bigtiger.kromise.AlwaysPipe
import com.bigtiger.kromise.FailPipe
import com.bigtiger.kromise.DonePipe
import com.bigtiger.kromise.FailFilter
import com.bigtiger.kromise.DoneFilter
import com.bigtiger.kromise.State

abstract class DelegatingKromise<D, F>(val delegate: Kromise<D, F>): Kromise<D, F> {

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

    override fun then(doneCallback: DoneCallback<in D>): Kromise<D, F> {
        return delegate.then(doneCallback)
    }

    override fun then(doneCallback: DoneCallback<in D>, failCallback: FailCallback<in F>): Kromise<D, F> {
        return delegate.then(doneCallback, failCallback)
    }


    override fun <D_OUT> then(doneFilter: DoneFilter<in D, out D_OUT>): Kromise<D_OUT, F> {
        return delegate.then(doneFilter)
    }

    override fun <D_OUT, F_OUT> then(doneFilter: DoneFilter<in D, out D_OUT>,
                                     failFilter: FailFilter<in F, out F_OUT>): Kromise<D_OUT, F_OUT> {
        return delegate.then(doneFilter, failFilter)
    }


    override fun <D_OUT> then(donePipe: DonePipe<in D, out D_OUT, out F>): Kromise<D_OUT, F> {
        return delegate.then(donePipe)
    }

    override fun <D_OUT, F_OUT> then(donePipe: DonePipe<in D, out D_OUT, out F_OUT>,
                                     failPipe: FailPipe<in F, out D_OUT, out F_OUT>): Kromise<D_OUT, F_OUT> {
        return delegate.then(donePipe, failPipe)
    }


    override fun <D_OUT> then(doneFilter: (D) -> D_OUT): Kromise<D_OUT, F> {
        return delegate.then(doneFilter)
    }

    override fun <D_OUT, F_OUT> then(doneFilter: (D) -> D_OUT, failFilter: (F) -> F_OUT): Kromise<D_OUT, F_OUT> {
        return delegate.then(doneFilter, failFilter)
    }

    override fun done(callback: (D?) -> Unit): Kromise<D, F> {
        return delegate.done(callback)
    }


    override fun <D_OUT, F_OUT> always(alwaysPipe: AlwaysPipe<in D, in F, out D_OUT, out F_OUT>): Kromise<D_OUT, F_OUT> {
        return delegate.always(alwaysPipe)
    }

    override fun done(callback: DoneCallback<in D>): Kromise<D, F> {
        return delegate.done(callback)
    }

    override fun fail(callback: FailCallback<in F>): Kromise<D, F> {
        return delegate.fail(callback)
    }

    override fun always(callback: AlwaysCallback<in D, in F>): Kromise<D, F> {
        return delegate.always(callback)
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