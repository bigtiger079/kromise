package com.bigtiger.kromise.impl

import com.bigtiger.kromise.multiple.OneResult
import com.bigtiger.kromise.multiple.MultipleResults3

internal class MutableMultipleResults3<V1, V2, V3>
    : AbstractMutableMultipleResults(3),
        MutableMultipleResults,
        MultipleResults3<V1, V2, V3> {

    private var v1: OneResult<V1>? = null
    private var v2: OneResult<V2>? = null
    private var v3: OneResult<V3>? = null

    protected fun setFirst(v1: OneResult<V1>) {
        super.set(0, v1)
        this.v1 = v1
    }

    protected fun setSecond(v2: OneResult<V2>) {
        super.set(1, v2)
        this.v2 = v2
    }

    protected fun setThird(v3: OneResult<V3>) {
        super.set(2, v3)
        this.v3 = v3
    }

    override fun getFirst(): OneResult<V1> {
        return v1!!
    }

    override fun getSecond(): OneResult<V2> {
        return v2!!
    }

    override fun getThird(): OneResult<V3> {
        return v3!!
    }

    override operator fun set(index: Int, result: OneResult<*>) {
        super.set(index, result)
        when (index) {
            0 -> this.v1 = result as OneResult<V1>
            1 -> this.v2 = result as OneResult<V2>
            2 -> this.v3 = result as OneResult<V3>
        }
    }

    override fun size(): Int {
        return 3
    }
}