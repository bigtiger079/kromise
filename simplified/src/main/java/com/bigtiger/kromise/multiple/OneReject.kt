package com.bigtiger.kromise.multiple

import com.bigtiger.kromise.Kromise

class OneReject<F>(index: Int, private val kromise: Kromise<*, F>, private val reject: F): AbstractOneValue<F>(index){
    override fun getValue(): F = reject

    fun getPromise():Kromise<*, F> {
        return kromise
    }

    fun getReject(): F {
        return reject
    }

    override fun toString(): String {
        return "OneReject [index=$mIndex, kromise=$kromise, reject=$reject]"
    }
}