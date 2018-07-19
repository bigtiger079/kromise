package com.bigtiger.kromise.impl

import com.bigtiger.kromise.multiple.MultipleResults
import com.bigtiger.kromise.multiple.OneResult

interface MutableMultipleResults: MultipleResults {
    fun set(index: Int, result: OneResult<*>)
}