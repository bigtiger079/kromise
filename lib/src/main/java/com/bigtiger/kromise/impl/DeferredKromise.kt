package com.bigtiger.kromise.impl

import com.bigtiger.kromise.Deferred

class DeferredKromise<D, F, P>(protected val deferred: Deferred<D, F, P>): DelegatingKromise<D, F, P>(deferred) {

}