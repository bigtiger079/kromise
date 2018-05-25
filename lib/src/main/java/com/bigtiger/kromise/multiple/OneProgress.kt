package com.bigtiger.kromise.multiple

import com.bigtiger.kromise.Kromise

class OneProgress<P>(done: Int, fail: Int, total: Int, private val mIndex: Int, private val kromise: Kromise<*, *, P>, private val progress: P):
        MasterProgress(done, fail, total), OneValue<P> {

    override fun getIndex(): Int {
        return mIndex
    }

    override fun getValue(): P {
        return progress
    }
	fun getPromise():  Kromise<*, *, P> {
		return kromise
	}

	fun getProgress(): P {
		return progress
	}

    override fun toString(): String {
        return "OneProgress [index=$mIndex, kromise=$kromise, progress=" + progress + ", getDone()=" + getDone() + ", getFail()=" + getFail() + ", getTotal()=" + getTotal()+ "]";
    }
}