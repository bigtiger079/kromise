package com.bigtiger.kromise.impl

import com.bigtiger.kromise.Deferred
import com.bigtiger.kromise.Kromise
import com.bigtiger.kromise.State


open class DeferredObject<D, F, P>: AbstractKromise<D, F, P>(), Deferred<D, F, P> {

    override fun resolve(resolve: D): Deferred<D, F, P> {
        synchronized(this) {
            if (!isPending())
                throw IllegalStateException("Deferred object already finished, cannot resolve again")

            this.state = State.RESOLVED
            this.resolveResult = resolve

            try {
                triggerDone(resolve)
            } finally {
                triggerAlways(state, resolve, null)
            }
        }
        return this
    }

    override fun notify(progress: P): Deferred<D, F, P> {
        synchronized(this) {
            if (!isPending())
                throw IllegalStateException("Deferred object already finished, cannot notify progress")

            triggerProgress(progress)
        }
        return this
    }

    override fun reject(reject: F): Deferred<D, F, P> {
        synchronized(this) {
            if (!isPending())
                throw IllegalStateException("Deferred object already finished, cannot reject again")
            this.state = State.REJECTED
            this.rejectResult = reject

            try {
                triggerFail(reject)
            } finally {
                triggerAlways(state, null, reject)
            }
        }
        return this
    }

    override fun kromise(): Kromise<D, F, P> {
        return this
    }
}