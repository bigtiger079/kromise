package com.bigtiger.kromise


interface ProgressFilter<P, P_OUT>{
    fun filterProgress(progress: P): P_OUT
}