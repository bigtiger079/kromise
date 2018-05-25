package com.bigtiger.kromise.impl

import com.bigtiger.kromise.StartPolicy
import android.system.Os.shutdown
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class DefaultDeferredManager(private val executorService: ExecutorService = Executors.newCachedThreadPool()): AbstractDeferredManager() {
    /**
     * By default, [.autoSubmit] will be set to true
     * You can set it to false by using [.setAutoSubmit]
     * If you set it to false, that means you'll be responsible to make sure any
     * [Runnable] or [Callable] are executed.
     */
    companion object {
        const val DEFAULT_AUTO_SUBMIT = true
    }


    private var autoSubmit = DEFAULT_AUTO_SUBMIT


    fun getExecutorService(): ExecutorService {
        return executorService
    }

    @Throws(InterruptedException::class)
    fun awaitTermination(timeout: Long, unit: TimeUnit): Boolean {
        return executorService.awaitTermination(timeout, unit)
    }

    fun isShutdown(): Boolean {
        return executorService.isShutdown()
    }

    fun isTerminated(): Boolean {
        return executorService.isTerminated()
    }

    fun shutdown() {
        executorService.shutdown()
    }

    fun shutdownNow(): List<Runnable> {
        return executorService.shutdownNow()
    }

    override fun submit(runnable: Runnable) {
        executorService.submit(runnable)
    }

    override fun submit(callable: Callable<*>) {
        executorService.submit(callable)
    }

    override fun isAutoSubmit(): Boolean {
        return autoSubmit
    }

    fun setAutoSubmit(autoSubmit: Boolean) {
        this.autoSubmit = autoSubmit
    }

}