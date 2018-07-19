package com.bigtiger.kromise.impl

import com.bigtiger.kromise.multiple.AllValues
import com.bigtiger.kromise.multiple.OneValue

interface MutableAllValues: AllValues {
    fun set(index: Int, result: OneValue<*>)
}