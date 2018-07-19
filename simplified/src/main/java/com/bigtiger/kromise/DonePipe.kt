package com.bigtiger.kromise

interface DonePipe<D, D_OUT, F_OUT> {
    fun pipeDone(result: D): Kromise<D_OUT, F_OUT>
}