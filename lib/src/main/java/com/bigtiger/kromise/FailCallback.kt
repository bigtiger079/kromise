package com.bigtiger.kromise

interface FailCallback<F> {
    fun onFail(result: F?)
}