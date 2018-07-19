package com.bigtiger.kromise.impl

import com.bigtiger.kromise.multiple.AllValues
import com.bigtiger.kromise.multiple.OneValue
import java.util.concurrent.CopyOnWriteArrayList

open class DefaultAllValues(size: Int): AllValues {
    protected val values: CopyOnWriteArrayList<OneValue<*>> = CopyOnWriteArrayList(arrayOfNulls(size))

    override fun get(index: Int): OneValue<*> {
        return values[index]
    }

    override fun iterator(): Iterator<OneValue<*>> {
        return values.iterator()
    }

    override fun size(): Int {
        return values.size
    }

    override fun toString(): String {
        return javaClass.simpleName + " [values=" + values + "]"
    }
}