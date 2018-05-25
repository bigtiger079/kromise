package com.bigtiger.kromise.impl

import com.bigtiger.kromise.Kromise

internal class MasterDeferredObjectN<V1, V2, V3, V4, V5>(promiseV1: Kromise<V1, *, *>,
                                                         promiseV2: Kromise<V2, *, *>,
                                                         promiseV3: Kromise<V3, *, *>,
                                                         promiseV4: Kromise<V4, *, *>,
                                                         promiseV5: Kromise<V5, *, *>,
                                                         promise6: Kromise<*, *, *>,
                                                         vararg promises: Kromise<*, *, *>) : AbstractMasterDeferredObject(MutableMultipleResultsN<V1, V2, V3, V4, V5>(6 + promises.size)) {
    init {
        configureKromise(0, promiseV1)
        configureKromise(1, promiseV2)
        configureKromise(2, promiseV3)
        configureKromise(3, promiseV4)
        configureKromise(4, promiseV5)
        configureKromise(5, promise6)

        var index = 6
        for (promise in promises) {
            configureKromise(index++, promise)
        }
    }
}