package com.bigtiger.kromise.impl

import com.bigtiger.kromise.Kromise

internal class MasterDeferredObject5<V1, V2, V3, V4, V5>(promiseV1: Kromise<V1, *, *>,
                                                         promiseV2: Kromise<V2, *, *>,
                                                         promiseV3: Kromise<V3, *, *>,
                                                         promiseV4: Kromise<V4, *, *>,
                                                         promiseV5: Kromise<V5, *, *>) : AbstractMasterDeferredObject(MutableMultipleResults5<V1, V2, V3, V4, V5>()) {
    init {
        configureKromise(0, promiseV1)
        configureKromise(1, promiseV2)
        configureKromise(2, promiseV3)
        configureKromise(3, promiseV4)
        configureKromise(4, promiseV5)
    }
}