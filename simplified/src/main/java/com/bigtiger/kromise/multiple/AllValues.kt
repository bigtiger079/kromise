package com.bigtiger.kromise.multiple

interface AllValues: Iterable<OneValue<*>> {

    fun get(index: Int):OneValue<*>
	fun size(): Int
}