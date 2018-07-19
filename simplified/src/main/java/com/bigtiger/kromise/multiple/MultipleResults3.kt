package com.bigtiger.kromise.multiple

interface MultipleResults3<V1, V2, V3>: MultipleResults {
    fun getFirst():OneResult<V1>
    fun getSecond(): OneResult<V2>
    fun getThird(): OneResult<V3>
}