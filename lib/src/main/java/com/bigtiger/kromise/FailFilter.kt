package com.bigtiger.kromise

interface FailFilter<F, F_OUT> {
    fun filterFail(result: F): F_OUT
}