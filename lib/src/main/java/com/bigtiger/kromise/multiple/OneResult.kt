package com.bigtiger.kromise.multiple

import com.bigtiger.kromise.Kromise

class OneResult<D>(index: Int, private val kromise: Kromise<D, *, *>, private val result: D): AbstractOneValue<D>(index) {


	override fun getValue(): D {
		return getResult()
	}

	fun getResult(): D  {
		return result
	}

	override fun toString(): String {
		return "OneResult [index=$mIndex , kromise=$kromise, result=$result]"
	}
}