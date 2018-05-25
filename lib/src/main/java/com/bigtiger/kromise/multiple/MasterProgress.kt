package com.bigtiger.kromise.multiple


open class MasterProgress(private val done: Int, private val fail: Int, private val total: Int) {

    fun getDone() = done

    fun getFail() = fail

    fun getTotal() = total

    override fun toString(): String {
        return ("MasterProgress [done=" + done + ", fail=" + fail
                + ", total=" + total + "]")
    }

}