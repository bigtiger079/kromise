package com.bigtiger.kromise


interface ProgressPipe<P, D_OUT, F_OUT, P_OUT> {
    fun pipeProgress(result: P): Kromise<D_OUT, F_OUT, P_OUT>
}