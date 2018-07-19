package com.bigtiger.kromise.multiple

interface MultipleResults: Iterable<OneResult<*>>{
    fun get(index: Int): OneResult<*>
    fun size(): Int
}