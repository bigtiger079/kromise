package com.bigtiger.kromise.multiple

interface OneValue<T> {

    fun getIndex(): Int
    fun getValue(): T
}