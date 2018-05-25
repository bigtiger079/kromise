package com.bigtiger.kromise.multiple

interface MultipleResults4<V1, V2, V3, V4>: MultipleResults {
    fun getFirst():OneResult<V1>
    fun getSecond(): OneResult<V2>
    fun getThird(): OneResult<V3>
    fun getFourth(): OneResult<V4>
}