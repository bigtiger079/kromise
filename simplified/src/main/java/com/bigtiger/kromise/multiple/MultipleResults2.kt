package com.bigtiger.kromise.multiple

interface MultipleResults2<V1, V2>: MultipleResults {
    fun getFirst():OneResult<V1>
    fun getSecond(): OneResult<V2>
}