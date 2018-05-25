package com.bigtiger.kromise


interface ProgressCallback<P> {
    fun onProgress(progress: P)
}