package com.bigtiger.kromise.multiple



abstract class AbstractOneValue<T>(val mIndex: Int): OneValue<T> {
    override fun getIndex(): Int  = mIndex
}