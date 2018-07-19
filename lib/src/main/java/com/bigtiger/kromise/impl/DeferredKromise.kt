package com.bigtiger.kromise.impl

import com.bigtiger.kromise.Deferred
import com.bigtiger.kromise.DoneFilter
import com.bigtiger.kromise.Kromise

class DeferredKromise<D, F, P>(protected val deferred: Deferred<D, F, P>): DelegatingKromise<D, F, P>(deferred) {

}