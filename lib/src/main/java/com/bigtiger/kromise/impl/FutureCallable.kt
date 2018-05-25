package com.bigtiger.kromise.impl

import java.util.concurrent.Callable
import java.util.concurrent.ExecutionException
import java.util.concurrent.Future


class FutureCallable<V>(private val future: Future<V>): Callable<V> {

    @Throws(Exception::class)
    override fun call(): V {
        try {
            return future.get()
        } catch (e: InterruptedException) {
            throw e
        } catch (a: ExecutionException) {
            if (a.cause is Exception)
                throw a.cause as Exception
            else
                throw a
        }

    }

}