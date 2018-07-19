package com.bigtiger.kromise

interface DoneCallback<D> {
    fun onDone(result:D?)
}