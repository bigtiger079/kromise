package com.bigtiger.kromise.impl

import com.bigtiger.kromise.Kromise


internal class MasterDeferredObjectUntypedN(promises: Array<Kromise<*, *>>)
    : AbstractMasterDeferredObject(MutableMultipleResultsUntypedN(promises.size)) {

    init {
        for (i in promises.indices) {
            configureKromise(i, promises[i])
        }
    }
}