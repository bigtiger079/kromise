package com.bigtiger.kromise

interface FailPipe<F, D_OUT, F_OUT, P_OUT> {
    fun pipeFail(result: F): Kromise<D_OUT, F_OUT, P_OUT>
}