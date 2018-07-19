package com.bigtiger.kromise.impl

import com.bigtiger.kromise.multiple.OneValue

class DefaultMutableAllValues(size: Int): DefaultAllValues(size), MutableAllValues {
    override fun set(index: Int, result: OneValue<*>) {
        values.set(index, result)
    }
}