package com.bigtiger.kromise

interface DoneFilter<D, D_OUT> {
    fun filterDone(result: D): D_OUT
}