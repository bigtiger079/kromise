package com.bigtiger.kromise.multiple

interface MultipleResults5<V1, V2, V3, V4, V5>: MultipleResults {
    fun getFirst():OneResult<V1>
    fun getSecond(): OneResult<V2>
    fun getThird(): OneResult<V3>
    fun getFourth(): OneResult<V4>
    fun getFifth(): OneResult<V5>
}