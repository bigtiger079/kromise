package com.bigtiger.kromise.impl

import com.bigtiger.kromise.Kromise


internal class MasterDeferredObject3<V1, V2, V3>(promiseV1: Kromise<V1, *>,
                                                 promiseV2: Kromise<V2, *>,
                                                 promiseV3: Kromise<V3, *>)
    : AbstractMasterDeferredObject(MutableMultipleResults3<V1, V2, V3>()) {

    init {
        configureKromise(0, promiseV1)
        configureKromise(1, promiseV2)
        configureKromise(2, promiseV3)
    }
}