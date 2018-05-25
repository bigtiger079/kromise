package com.bigtiger.kromise.impl

import com.bigtiger.kromise.multiple.MultipleResults2
import com.bigtiger.kromise.multiple.OneResult





class MutableMultipleResults2<V1, V2>: AbstractMutableMultipleResults(2), MutableMultipleResults, MultipleResults2<V1, V2> {

    private lateinit var v1: OneResult<V1>
    private lateinit var v2: OneResult<V2>

    protected fun setFirst(v1: OneResult<V1>) {
        super.set(0, v1)
        this.v1 = v1
    }

    protected fun setSecond(v2: OneResult<V2>) {
        super.set(1, v2)
        this.v2 = v2
    }


    override fun getFirst(): OneResult<V1> {
        return v1
    }

    override fun getSecond(): OneResult<V2> {
        return v2
    }

    override operator fun set(index: Int, result: OneResult<*>) {
        super.set(index, result)
        when (index) {
            0 -> this.v1 = result as OneResult<V1>
            1 -> this.v2 = result as OneResult<V2>
        }
    }

    override fun size(): Int {
        return 2
    }

}