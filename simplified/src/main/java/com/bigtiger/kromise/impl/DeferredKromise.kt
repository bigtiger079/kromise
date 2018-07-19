package com.bigtiger.kromise.impl

import com.bigtiger.kromise.Deferred
import com.bigtiger.kromise.DoneFilter
import com.bigtiger.kromise.Kromise

class DeferredKromise<D, F>(protected val deferred: Deferred<D, F>): DelegatingKromise<D, F>(deferred) {

}