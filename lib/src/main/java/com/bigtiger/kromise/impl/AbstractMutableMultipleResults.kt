package com.bigtiger.kromise.impl

import com.bigtiger.kromise.multiple.OneResult

abstract class AbstractMutableMultipleResults(size: Int): AbstractMultipleResults(size), MutableMultipleResults {
    override fun set(index: Int, result: OneResult<*>) {
        results[index] = result
    }
}