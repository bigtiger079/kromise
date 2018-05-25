package com.bigtiger.kromise.impl

import com.bigtiger.kromise.Kromise

class MasterDeferredObject2<V1,V2>(kromiseV1: Kromise<V1, *, *>,
                                   kromiseV2: Kromise<V2, *, *>): AbstractMasterDeferredObject(MutableMultipleResults2<V1, V2>()) {
    init {
        configureKromise(0, kromiseV1)
        configureKromise(1, kromiseV2)
    }
}