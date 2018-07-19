package com.bigtiger.kromise.impl

import com.bigtiger.kromise.multiple.MultipleResults
import com.bigtiger.kromise.multiple.OneResult
import java.util.concurrent.CopyOnWriteArrayList

abstract class AbstractMultipleResults(size: Int): MultipleResults {
    protected val results: CopyOnWriteArrayList<OneResult<*>> = CopyOnWriteArrayList(arrayOfNulls(size))

    override fun get(index: Int):OneResult<*> {
		return results.get(index)
	}

	override fun iterator(): Iterator<OneResult<*>> {
		return results.iterator()
	}

    override fun size(): Int = results.size

    override fun toString(): String {
        return javaClass.simpleName + " [results=" + results + "]"
    }
}