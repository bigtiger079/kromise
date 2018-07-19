package com.bigtiger.kromise

import com.bigtiger.kromise.impl.DeferredObject
import java.util.concurrent.Callable
import java.util.concurrent.CancellationException
import java.util.concurrent.ExecutionException
import java.util.concurrent.FutureTask

class DeferredFutureTask<D> : FutureTask<D> {

    protected val deferred: Deferred<D, Throwable>
    val startPolicy: StartPolicy
    private var taskDelegate: Any? = null
    private var cancellationHandler: CancellationHandler? = null

    /**
     * Creates a new `DeferredFutureTask` with the given task.
     * The given task may implement the `CancellationHandler` interface.
     *
     * @param task the task to be executed. Must not be null.
     */
    constructor(task: DeferredRunnable) : this(task, null) {}

    /**
     * Creates a new `DeferredFutureTask` with the given task and a explicit `CancellationHandler`
     * The given `cancellationHandler` has precedence over the given task if the task implements the `CancellationHandler` interface.
     *
     * @param task                the task to be executed. Must not be null.
     * @param cancellationHandler the `CancellationHandler` to invoke during onCancel. May be null.
     *
     * @since 2.0
     */
    @JvmOverloads constructor(task: Callable<D>, cancellationHandler: CancellationHandler? = null) : super(task) {
        this.taskDelegate = task
        this.cancellationHandler = cancellationHandler
        this.deferred = DeferredObject<D, Throwable>()
        this.startPolicy = StartPolicy.DEFAULT
    }

    /**
     * Creates a new `DeferredFutureTask` with the given task and a explicit `CancellationHandler`
     * The given `cancellationHandler` has precedence over the given task if the task implements the `CancellationHandler` interface.
     *
     * @param task                the task to be executed. Must not be null.
     * @param cancellationHandler the `CancellationHandler` to invoke during onCancel. May be null.
     *
     * @since 2.0
     */
    @JvmOverloads constructor(task: Runnable, cancellationHandler: CancellationHandler? = null) : super(task, null) {
        this.taskDelegate = task
        this.cancellationHandler = cancellationHandler
        this.deferred = DeferredObject<D, Throwable>()
        this.startPolicy = StartPolicy.DEFAULT
    }

    /**
     * Creates a new `DeferredFutureTask` with the given task and a explicit `CancellationHandler`
     * The given `cancellationHandler` has precedence over the given task if the task implements the `CancellationHandler` interface.
     *
     * @param task                the task to be executed. Must not be null.
     * @param cancellationHandler the `CancellationHandler` to invoke during onCancel. May be null.
     *
     * @since 2.0
     */
    @JvmOverloads constructor(task: DeferredCallable<D>, cancellationHandler: CancellationHandler? = null) : super(task) {
        this.taskDelegate = task
        this.cancellationHandler = cancellationHandler
        this.deferred = task.getDeferred()
        this.startPolicy = task.getStartPolicy()
    }

    /**
     * Creates a new `DeferredFutureTask` with the given task and a explicit `CancellationHandler`
     * The given `cancellationHandler` has precedence over the given task if the task implements the `CancellationHandler` interface.
     *
     * @param task                the task to be executed. Must not be null.
     * @param cancellationHandler the `CancellationHandler` to invoke during onCancel. May be null.
     *
     * @since 2.0
     */
    constructor(task: DeferredRunnable, cancellationHandler: CancellationHandler?) : super(task, null) {
        this.taskDelegate = task
        this.cancellationHandler = cancellationHandler
        this.deferred = task.getDeferred() as Deferred<D, Throwable>
        this.startPolicy = task.getStartPolicy()
    }

    fun kromise(): Kromise<D, Throwable> {
        return deferred.kromise()
    }

    override protected fun done() {
        if (isCancelled()) {
            deferred.reject(CancellationException())
            cleanup()
            return
        }

        try {
            deferred.resolve(get())
        } catch (e: InterruptedException) {
            try {
                deferred.reject(causeOf(e))
            } finally {
                cleanup()
            }
        } catch (e: ExecutionException) {
            try {
                deferred.reject(causeOf(e))
            } finally {
                cleanup()
            }
        } catch (t: Throwable) {
            // TODO: forward to global ExceptionHandler
            //LOG.warn("Unexpected error when resolving value", t)
        }

    }

    protected fun causeOf(e: Exception): Throwable {
        return if (e.cause != null) e.cause!! else e
    }

    /**
     * Performs resource cleanup upon interruption or cancellation of the underlying task.
     * This method gives precedence to `cancellationHandler` it not null, otherwise
     * it invokes the underlying task's `onCancel()` if it implements the `CancellationHandler` interface.
     *
     * @since 2.0
     */
    protected fun cleanup() {
        try {
            if (cancellationHandler != null) {
                cancellationHandler!!.onCancel()
            } else if (taskDelegate is CancellationHandler) {
                (taskDelegate as CancellationHandler).onCancel()
            }
        } catch (t: Throwable) {
            // TODO: forward to global ExceptionHandler
            //LOG.warn("Unexpected error when cleaning up", t)
        }

    }

    companion object {
        //private val LOG = LoggerFactory.getLogger(DeferredFutureTask<*, *>::class.java)
    }
}