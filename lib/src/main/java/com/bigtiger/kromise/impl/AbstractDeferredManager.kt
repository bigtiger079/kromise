package com.bigtiger.kromise.impl

import com.bigtiger.kromise.*
import java.util.concurrent.Callable
import com.bigtiger.kromise.multiple.MasterProgress
import com.bigtiger.kromise.multiple.AllValues
import com.bigtiger.kromise.multiple.OneReject
import com.bigtiger.kromise.multiple.OneResult
import com.bigtiger.kromise.multiple.MultipleResults
import com.bigtiger.kromise.multiple.MultipleResultsN
import com.bigtiger.kromise.multiple.MultipleResults5
import com.bigtiger.kromise.multiple.MultipleResults4
import com.bigtiger.kromise.multiple.MultipleResults3
import com.bigtiger.kromise.multiple.MultipleResults2
import java.util.*
import java.util.concurrent.ExecutionException
import java.util.concurrent.Future
import kotlin.collections.ArrayList


abstract class AbstractDeferredManager: DeferredManager {
    companion object {
        protected const val PROMISE_V1 = "kromiseV1"
        protected const val PROMISE_V2 = "kromiseV2"
        protected const val PROMISE_V3 = "kromiseV3"
        protected const val PROMISE_V4 = "kromiseV4"
        protected const val PROMISE_V5 = "kromiseV5"

        protected const val CALLABLE_V1 = "callableV1"
        protected const val CALLABLE_V2 = "callableV2"
        protected const val CALLABLE_V3 = "callableV3"
        protected const val CALLABLE_V4 = "callableV4"
        protected const val CALLABLE_V5 = "callableV5"

        protected const val RUNNABLE_V1 = "runnableV1"
        protected const val RUNNABLE_V2 = "runnableV2"
        protected const val RUNNABLE_V3 = "runnableV3"
        protected const val RUNNABLE_V4 = "runnableV4"
        protected const val RUNNABLE_V5 = "runnableV5"

        protected const val TASK_V1 = "taskV1"
        protected const val TASK_V2 = "taskV2"
        protected const val TASK_V3 = "taskV3"
        protected const val TASK_V4 = "taskV4"
        protected const val TASK_V5 = "taskV5"

        protected const val FUTURE_V1 = "futureV1"
        protected const val FUTURE_V2 = "futureV2"
        protected const val FUTURE_V3 = "futureV3"
        protected const val FUTURE_V4 = "futureV4"
        protected const val FUTURE_V5 = "futureV5"
    }

    protected abstract fun submit(runnable: Runnable)

    protected abstract fun submit(callable: Callable<*>)

    abstract fun isAutoSubmit(): Boolean

    override fun <F, V1, V2> `when`(kromiseV1: Kromise<V1, *, *>, kromiseV2: Kromise<V2, *, *>)
            : Kromise<MultipleResults2<V1, V2>, OneReject<F>, MasterProgress> {
        assertNotNull(kromiseV1, PROMISE_V1)
        assertNotNull(kromiseV2, PROMISE_V2)
        return MasterDeferredObject2(kromiseV1, kromiseV2) as Kromise<MultipleResults2<V1, V2>, OneReject<F>, MasterProgress>
    }

    override fun <F, V1, V2, V3> `when`(kromiseV1: Kromise<V1, *, *>, kromiseV2: Kromise<V2, *, *>, kromiseV3: Kromise<V3, *, *>)
            : Kromise<MultipleResults3<V1, V2, V3>, OneReject<F>, MasterProgress> {
        assertNotNull(kromiseV1, PROMISE_V1)
        assertNotNull(kromiseV2, PROMISE_V2)
        assertNotNull(kromiseV3, PROMISE_V3)
        return MasterDeferredObject3(kromiseV1, kromiseV2, kromiseV3) as Kromise<MultipleResults3<V1, V2, V3>, OneReject<F>, MasterProgress>
    }

    override fun <F, V1, V2, V3, V4> `when`(kromiseV1: Kromise<V1, *, *>,
                                            kromiseV2: Kromise<V2, *, *>,
                                            kromiseV3: Kromise<V3, *, *>,
                                            kromiseV4: Kromise<V4, *, *>)
            : Kromise<MultipleResults4<V1, V2, V3, V4>, OneReject<F>, MasterProgress> {
        assertNotNull(kromiseV1, PROMISE_V1)
        assertNotNull(kromiseV2, PROMISE_V2)
        assertNotNull(kromiseV3, PROMISE_V3)
        assertNotNull(kromiseV4, PROMISE_V4)
        return MasterDeferredObject4(kromiseV1, kromiseV2, kromiseV3, kromiseV4) as Kromise<MultipleResults4<V1, V2, V3, V4>, OneReject<F>, MasterProgress>
    }

    override fun <F, V1, V2, V3, V4, V5> `when`(kromiseV1: Kromise<V1, *, *>,
                                                kromiseV2: Kromise<V2, *, *>,
                                                kromiseV3: Kromise<V3, *, *>,
                                                kromiseV4: Kromise<V4, *, *>,
                                                kromiseV5: Kromise<V5, *, *>)
            : Kromise<MultipleResults5<V1, V2, V3, V4, V5>, OneReject<F>, MasterProgress> {
        assertNotNull(kromiseV1, PROMISE_V1)
        assertNotNull(kromiseV2, PROMISE_V2)
        assertNotNull(kromiseV3, PROMISE_V3)
        assertNotNull(kromiseV4, PROMISE_V4)
        assertNotNull(kromiseV5, PROMISE_V5)
        return MasterDeferredObject5(kromiseV1, kromiseV2, kromiseV3, kromiseV4, kromiseV5) as Kromise<MultipleResults5<V1, V2, V3, V4, V5>, OneReject<F>, MasterProgress>
    }

    override fun <F, V1, V2, V3, V4, V5> `when`(kromiseV1: Kromise<V1, *, *>,
                                                kromiseV2: Kromise<V2, *, *>,
                                                kromiseV3: Kromise<V3, *, *>,
                                                kromiseV4: Kromise<V4, *, *>,
                                                kromiseV5: Kromise<V5, *, *>,
                                                kromise6: Kromise<*, *, *>,
                                                vararg kromises: Kromise<*, *, *>)
            : Kromise<MultipleResultsN<V1, V2, V3, V4, V5>, OneReject<F>, MasterProgress> {

        assertNotNull(kromiseV1, PROMISE_V1)
        assertNotNull(kromiseV2, PROMISE_V2)
        assertNotNull(kromiseV3, PROMISE_V3)
        assertNotNull(kromiseV4, PROMISE_V4)
        assertNotNull(kromiseV5, PROMISE_V5)
        assertNotNull(kromise6, "kromise6")

        val kromiseN = arrayOfNulls<Kromise<*, *, *>>(kromises.size - 5)
        System.arraycopy(kromises, 5, kromiseN, 0, kromiseN.size)

        return MasterDeferredObjectN(kromiseV1, kromiseV2, kromiseV3, kromiseV4, kromiseV5, kromise6, *kromiseN as Array<out Kromise<*, *, *>>) as Kromise<MultipleResultsN<V1, V2, V3, V4, V5>, OneReject<F>, MasterProgress>
    }

    override fun `when`(runnable1: Runnable, runnable2: Runnable, vararg runnables: Runnable): Kromise<MultipleResults, OneReject<Throwable>, MasterProgress> {
        assertNotNull(runnable1, "runnable1")
        assertNotNull(runnable2, "runnable2")
        val kromises = arrayOf(`when`(runnable1), `when`(runnable2))//arrayOfNulls<Kromise>(runnables.size + 2)
//        kromises[0] = `when`(runnable1)
//        kromises[1] = `when`(runnable2)

        for (i in runnables.indices) {
            if (runnables[i] is DeferredRunnable<*>) {
                kromises[i + 2] = `when`(runnables[i])
            } else {
                kromises[i + 2] = `when`(runnables[i])
            }
        }

        when (kromises.size) {
            2 -> return `when`(kromises[0], kromises[1])
            3 -> return `when`(kromises[0], kromises[1], kromises[2])
            4 -> return `when`(kromises[0], kromises[1], kromises[2], kromises[3])
            5 -> return `when`(kromises[0], kromises[1], kromises[2], kromises[3], kromises[4])
            else -> {
                val kromiseN = arrayOfNulls<Kromise<*, *, *>>(kromises.size - 5)
                System.arraycopy(kromises, 5, kromiseN, 0, kromiseN.size)
                return MasterDeferredObjectN(kromises[0], kromises[1], kromises[2], kromises[3], kromises[4], kromises[5], *kromiseN)
            }
        }
    }

    override fun <V1, V2> `when`(callableV1: Callable<V1>, callableV2: Callable<V2>)
            : Kromise<MultipleResults2<V1, V2>, OneReject<Throwable>, MasterProgress> {
        assertNotNull(callableV1, CALLABLE_V1)
        assertNotNull(callableV2, CALLABLE_V2)
        return MasterDeferredObject2(`when`(callableV1), `when`(callableV2)) as Kromise<MultipleResults2<V1, V2>, OneReject<Throwable>, MasterProgress>
    }

    override fun <V1, V2, V3> `when`(callableV1: Callable<V1>, callableV2: Callable<V2>, callableV3: Callable<V3>)
            : Kromise<MultipleResults3<V1, V2, V3>, OneReject<Throwable>, MasterProgress> {
        assertNotNull(callableV1, CALLABLE_V1)
        assertNotNull(callableV2, CALLABLE_V2)
        assertNotNull(callableV3, CALLABLE_V3)
        return MasterDeferredObject3(`when`(callableV1), `when`(callableV2), `when`(callableV3)) as Kromise<MultipleResults3<V1, V2, V3>, OneReject<Throwable>, MasterProgress>
    }

    override fun <V1, V2, V3, V4> `when`(callableV1: Callable<V1>, callableV2: Callable<V2>, callableV3: Callable<V3>, callableV4: Callable<V4>)
            : Kromise<MultipleResults4<V1, V2, V3, V4>, OneReject<Throwable>, MasterProgress> {
        assertNotNull(callableV1, CALLABLE_V1)
        assertNotNull(callableV2, CALLABLE_V2)
        assertNotNull(callableV3, CALLABLE_V3)
        assertNotNull(callableV4, CALLABLE_V4)
        return MasterDeferredObject4(`when`(callableV1), `when`(callableV2), `when`(callableV3), `when`(callableV4)) as Kromise<MultipleResults4<V1, V2, V3, V4>, OneReject<Throwable>, MasterProgress>
    }

    override fun <V1, V2, V3, V4, V5> `when`(callableV1: Callable<V1>,
                                             callableV2: Callable<V2>,
                                             callableV3: Callable<V3>,
                                             callableV4: Callable<V4>,
                                             callableV5: Callable<V5>)
            : Kromise<MultipleResults5<V1, V2, V3, V4, V5>, OneReject<Throwable>, MasterProgress> {

        assertNotNull(callableV1, CALLABLE_V1)
        assertNotNull(callableV2, CALLABLE_V2)
        assertNotNull(callableV3, CALLABLE_V3)
        assertNotNull(callableV4, CALLABLE_V4)
        assertNotNull(callableV5, CALLABLE_V5)
        return MasterDeferredObject5(`when`(callableV1), `when`(callableV2), `when`(callableV3), `when`(callableV4), `when`(callableV5)) as Kromise<MultipleResults5<V1, V2, V3, V4, V5>, OneReject<Throwable>, MasterProgress>
    }

    override fun <V1, V2, V3, V4, V5> `when`(callableV1: Callable<V1>,
                                             callableV2: Callable<V2>,
                                             callableV3: Callable<V3>,
                                             callableV4: Callable<V4>,
                                             callableV5: Callable<V5>, callable6: Callable<*>, vararg callables: Callable<*>): Kromise<MultipleResultsN<V1, V2, V3, V4, V5>, OneReject<Throwable>, MasterProgress> {
        assertNotNull(callableV1, CALLABLE_V1)
        assertNotNull(callableV2, CALLABLE_V2)
        assertNotNull(callableV3, CALLABLE_V3)
        assertNotNull(callableV4, CALLABLE_V4)
        assertNotNull(callableV5, CALLABLE_V5)
        assertNotNull(callable6, "callable6")

        val kromise1 = `when`(callableV1)
        val kromise2 = `when`(callableV2)
        val kromise3 = `when`(callableV3)
        val kromise4 = `when`(callableV4)
        val kromise5 = `when`(callableV5)

        val kromiseN = arrayOfNulls<Kromise<*, *, *>>(callables.size)
        for (i in callables.indices) {
            if (callables[i] is DeferredCallable<*, *>) {
                kromiseN[i] = `when`(callables[i] as DeferredCallable<*, *>)
            } else {
                kromiseN[i] = `when`(callables[i])
            }
        }
        return MasterDeferredObjectN(kromise1, kromise2, kromise3, kromise4, kromise5, `when`(callable6), kromiseN)
    }

    override fun <P1, P2> `when`(
            runnableP1: DeferredRunnable<P1>,
            runnableP2: DeferredRunnable<P2>): Kromise<MultipleResults2<Void, Void>, OneReject<Throwable>, MasterProgress> {
        assertNotNull(runnableP1, RUNNABLE_V1)
        assertNotNull(runnableP2, RUNNABLE_V2)
        return MasterDeferredObject2(`when`<P1>(runnableP1), `when`<P2>(runnableP2))
    }

    override fun <P1, P2, P3> `when`(
            runnableP1: DeferredRunnable<P1>,
            runnableP2: DeferredRunnable<P2>,
            runnableP3: DeferredRunnable<P3>): Kromise<MultipleResults3<Void, Void, Void>, OneReject<Throwable>, MasterProgress> {
        assertNotNull(runnableP1, RUNNABLE_V1)
        assertNotNull(runnableP2, RUNNABLE_V2)
        assertNotNull(runnableP3, RUNNABLE_V3)
        return MasterDeferredObject3(`when`<P1>(runnableP1), `when`<P2>(runnableP2), `when`<P3>(runnableP3))
    }

    override fun <P1, P2, P3, P4> `when`(
            runnableP1: DeferredRunnable<P1>,
            runnableP2: DeferredRunnable<P2>,
            runnableP3: DeferredRunnable<P3>,
            runnableP4: DeferredRunnable<P4>): Kromise<MultipleResults4<Void, Void, Void, Void>, OneReject<Throwable>, MasterProgress> {
        assertNotNull(runnableP1, RUNNABLE_V1)
        assertNotNull(runnableP2, RUNNABLE_V2)
        assertNotNull(runnableP3, RUNNABLE_V3)
        assertNotNull(runnableP4, RUNNABLE_V4)
        return MasterDeferredObject4(`when`<P1>(runnableP1), `when`<P2>(runnableP2), `when`<P3>(runnableP3), `when`<P4>(runnableP4))
    }

    override fun <P1, P2, P3, P4, P5> `when`(
            runnableP1: DeferredRunnable<P1>,
            runnableP2: DeferredRunnable<P2>,
            runnableP3: DeferredRunnable<P3>,
            runnableP4: DeferredRunnable<P4>,
            runnableP5: DeferredRunnable<P5>): Kromise<MultipleResults5<Void, Void, Void, Void, Void>, OneReject<Throwable>, MasterProgress> {
        assertNotNull(runnableP1, RUNNABLE_V1)
        assertNotNull(runnableP2, RUNNABLE_V2)
        assertNotNull(runnableP3, RUNNABLE_V3)
        assertNotNull(runnableP4, RUNNABLE_V4)
        assertNotNull(runnableP5, RUNNABLE_V5)
        return MasterDeferredObject5(`when`(runnableP1), `when`(runnableP2), `when`(runnableP3), `when`(runnableP4), `when`(runnableP5))
    }

    override fun <P1, P2, P3, P4, P5> `when`(
            runnableP1: DeferredRunnable<P1>,
            runnableP2: DeferredRunnable<P2>,
            runnableP3: DeferredRunnable<P3>,
            runnableP4: DeferredRunnable<P4>,
            runnableP5: DeferredRunnable<P5>,
            runnable6: DeferredRunnable<*>,
            vararg runnables: DeferredRunnable<*>): Kromise<MultipleResultsN<Void, Void, Void, Void, Void>, OneReject<Throwable>, MasterProgress> {
        assertNotNull(runnableP1, RUNNABLE_V1)
        assertNotNull(runnableP2, RUNNABLE_V2)
        assertNotNull(runnableP3, RUNNABLE_V3)
        assertNotNull(runnableP4, RUNNABLE_V4)
        assertNotNull(runnableP5, RUNNABLE_V5)
        assertNotNull(runnable6, "runnable6")

        val kromise1 = `when`(runnableP1)
        val kromise2 = `when`(runnableP2)
        val kromise3 = `when`(runnableP3)
        val kromise4 = `when`(runnableP4)
        val kromise5 = `when`(runnableP5)
        val kromise6 = `when`(runnable6)

        val kromiseN = arrayOfNulls<Kromise<*,*,*>>(runnables.size)
        for (i in runnables.indices) {
            kromiseN[i] = `when`(runnables[i])
        }
        return MasterDeferredObjectN(kromise1, kromise2, kromise3, kromise4, kromise5, kromise6, kromiseN)
    }

    override fun <V1, V2> `when`(callableV1: DeferredCallable<V1, *>, callableV2: DeferredCallable<V2, *>): Kromise<MultipleResults2<V1, V2>, OneReject<Throwable>, MasterProgress> {
        assertNotNull(callableV1, CALLABLE_V1)
        assertNotNull(callableV2, CALLABLE_V2)
        return MasterDeferredObject2(`when`<V1, *>(callableV1), `when`<V2, *>(callableV2))
    }

    override fun <V1, V2, V3> `when`(callableV1: DeferredCallable<V1, *>, callableV2: DeferredCallable<V2, *>, callableV3: DeferredCallable<V3, *>): Kromise<MultipleResults3<V1, V2, V3>, OneReject<Throwable>, MasterProgress> {
        assertNotNull(callableV1, CALLABLE_V1)
        assertNotNull(callableV2, CALLABLE_V2)
        assertNotNull(callableV3, CALLABLE_V3)
        return MasterDeferredObject3(`when`<V1, *>(callableV1), `when`<V2, *>(callableV2), `when`<V3, *>(callableV3))
    }

    override fun <V1, V2, V3, V4> `when`(callableV1: DeferredCallable<V1, *>, callableV2: DeferredCallable<V2, *>, callableV3: DeferredCallable<V3, *>, callableV4: DeferredCallable<V4, *>): Kromise<MultipleResults4<V1, V2, V3, V4>, OneReject<Throwable>, MasterProgress> {
        assertNotNull(callableV1, CALLABLE_V1)
        assertNotNull(callableV2, CALLABLE_V2)
        assertNotNull(callableV3, CALLABLE_V3)
        assertNotNull(callableV4, CALLABLE_V4)
        return MasterDeferredObject4(`when`<V1, *>(callableV1), `when`<V2, *>(callableV2), `when`<V3, *>(callableV3), `when`<V4, *>(callableV4))
    }

    override fun <V1, V2, V3, V4, V5> `when`(callableV1: DeferredCallable<V1, *>, callableV2: DeferredCallable<V2, *>, callableV3: DeferredCallable<V3, *>, callableV4: DeferredCallable<V4, *>, callableV5: DeferredCallable<V5, *>): Kromise<MultipleResults5<V1, V2, V3, V4, V5>, OneReject<Throwable>, MasterProgress> {
        assertNotNull(callableV1, CALLABLE_V1)
        assertNotNull(callableV2, CALLABLE_V2)
        assertNotNull(callableV3, CALLABLE_V3)
        assertNotNull(callableV4, CALLABLE_V4)
        assertNotNull(callableV5, CALLABLE_V5)
        return MasterDeferredObject5(`when`<V1, *>(callableV1), `when`<V2, *>(callableV2), `when`<V3, *>(callableV3), `when`<V4, *>(callableV4), `when`<V5, *>(callableV5))
    }

    override fun <V1, V2, V3, V4, V5> `when`(callableV1: DeferredCallable<V1, *>, callableV2: DeferredCallable<V2, *>, callableV3: DeferredCallable<V3, *>, callableV4: DeferredCallable<V4, *>, callableV5: DeferredCallable<V5, *>, callable6: DeferredCallable<*, *>, vararg callables: DeferredCallable<*, *>): Kromise<MultipleResultsN<V1, V2, V3, V4, V5>, OneReject<Throwable>, MasterProgress> {
        assertNotNull(callableV1, CALLABLE_V1)
        assertNotNull(callableV2, CALLABLE_V2)
        assertNotNull(callableV3, CALLABLE_V3)
        assertNotNull(callableV4, CALLABLE_V4)
        assertNotNull(callableV5, CALLABLE_V5)
        assertNotNull(callable6, "callable6")

        val kromise1 = `when`<V1>(callableV1)
        val kromise2 = `when`<V2>(callableV2)
        val kromise3 = `when`<V3>(callableV3)
        val kromise4 = `when`<V4>(callableV4)
        val kromise5 = `when`<V5>(callableV5)
        val kromise6 = `when`(callable6)

        val kromiseN = arrayOfNulls<Kromise<*, *, *>>(callables.size)
        for (i in callables.indices) {
            kromiseN[i] = `when`(callables[i])
        }
        return MasterDeferredObjectN(kromise1, kromise2, kromise3, kromise4, kromise5, kromise6, kromiseN)
    }

    override fun <V1, V2> `when`(taskV1: DeferredFutureTask<V1, *>, taskV2: DeferredFutureTask<V2, *>): Kromise<MultipleResults2<V1, V2>, OneReject<Throwable>, MasterProgress> {
        assertNotNull(taskV1, TASK_V1)
        assertNotNull(taskV2, TASK_V2)
        return MasterDeferredObject2(`when`<V1, *>(taskV1), `when`<V2, *>(taskV2))
    }

    override fun <V1, V2, V3> `when`(taskV1: DeferredFutureTask<V1, *>, taskV2: DeferredFutureTask<V2, *>, taskV3: DeferredFutureTask<V3, *>): Kromise<MultipleResults3<V1, V2, V3>, OneReject<Throwable>, MasterProgress> {
        assertNotNull(taskV1, TASK_V1)
        assertNotNull(taskV2, TASK_V2)
        assertNotNull(taskV3, TASK_V3)
        return MasterDeferredObject3(`when`<V1, *>(taskV1), `when`<V2, *>(taskV2), `when`<V3, *>(taskV3))
    }

    override fun <V1, V2, V3, V4> `when`(taskV1: DeferredFutureTask<V1, *>, taskV2: DeferredFutureTask<V2, *>, taskV3: DeferredFutureTask<V3, *>, taskV4: DeferredFutureTask<V4, *>): Kromise<MultipleResults4<V1, V2, V3, V4>, OneReject<Throwable>, MasterProgress> {
        assertNotNull(taskV1, TASK_V1)
        assertNotNull(taskV2, TASK_V2)
        assertNotNull(taskV3, TASK_V3)
        assertNotNull(taskV4, TASK_V4)
        return MasterDeferredObject4(`when`<V1, *>(taskV1), `when`<V2, *>(taskV2), `when`<V3, *>(taskV3), `when`<V4, *>(taskV4))
    }

    override fun <V1, V2, V3, V4, V5> `when`(taskV1: DeferredFutureTask<V1, *>, taskV2: DeferredFutureTask<V2, *>, taskV3: DeferredFutureTask<V3, *>, taskV4: DeferredFutureTask<V4, *>, taskV5: DeferredFutureTask<V5, *>): Kromise<MultipleResults5<V1, V2, V3, V4, V5>, OneReject<Throwable>, MasterProgress> {
        assertNotNull(taskV1, TASK_V1)
        assertNotNull(taskV2, TASK_V2)
        assertNotNull(taskV3, TASK_V3)
        assertNotNull(taskV4, TASK_V4)
        assertNotNull(taskV5, TASK_V5)
        return MasterDeferredObject5(`when`(taskV1), `when`(taskV2), `when`(taskV3), `when`(taskV4), `when`(taskV5))
    }

    override fun <V1, V2, V3, V4, V5> `when`(taskV1: DeferredFutureTask<V1, *>, taskV2: DeferredFutureTask<V2, *>, taskV3: DeferredFutureTask<V3, *>, taskV4: DeferredFutureTask<V4, *>, taskV5: DeferredFutureTask<V5, *>, task6: DeferredFutureTask<*, *>, vararg tasks: DeferredFutureTask<*, *>): Kromise<MultipleResultsN<V1, V2, V3, V4, V5>, OneReject<Throwable>, MasterProgress> {
        assertNotNull(taskV1, TASK_V1)
        assertNotNull(taskV2, TASK_V2)
        assertNotNull(taskV3, TASK_V3)
        assertNotNull(taskV4, TASK_V4)
        assertNotNull(taskV5, TASK_V5)
        assertNotNull(task6, "task6")

        val kromise1 = `when`(taskV1)
        val kromise2 = `when`(taskV2)
        val kromise3 = `when`(taskV3)
        val kromise4 = `when`(taskV4)
        val kromise5 = `when`(taskV5)
        val kromise6 = `when`(task6)

        val kromiseN = arrayOfNulls<Kromise<*,*,*>>(tasks.size)
        for (i in tasks.indices) {
            kromiseN[i] = `when`(tasks[i])
        }
        return MasterDeferredObjectN(kromise1, kromise2, kromise3, kromise4, kromise5, kromise6, kromiseN)
    }

    override fun <V1, V2> `when`(futureV1: Future<V1>, futureV2: Future<V2>): Kromise<MultipleResults2<V1, V2>, OneReject<Throwable>, MasterProgress> {
        assertNotNull(futureV1, FUTURE_V1)
        assertNotNull(futureV2, FUTURE_V2)
        return MasterDeferredObject2(`when`(futureV1), `when`(futureV2))
    }

    override fun <V1, V2, V3> `when`(futureV1: Future<V1>, futureV2: Future<V2>, futureV3: Future<V3>): Kromise<MultipleResults3<V1, V2, V3>, OneReject<Throwable>, MasterProgress> {
        assertNotNull(futureV1, FUTURE_V1)
        assertNotNull(futureV2, FUTURE_V2)
        assertNotNull(futureV3, FUTURE_V3)
        return MasterDeferredObject3(`when`(futureV1), `when`(futureV2), `when`(futureV3))
    }

    override fun <V1, V2, V3, V4> `when`(futureV1: Future<V1>, futureV2: Future<V2>, futureV3: Future<V3>, futureV4: Future<V4>): Kromise<MultipleResults4<V1, V2, V3, V4>, OneReject<Throwable>, MasterProgress> {
        assertNotNull(futureV1, FUTURE_V1)
        assertNotNull(futureV2, FUTURE_V2)
        assertNotNull(futureV3, FUTURE_V3)
        assertNotNull(futureV4, FUTURE_V4)
        return MasterDeferredObject4(`when`(futureV1), `when`(futureV2), `when`(futureV3), `when`(futureV4))
    }

    override fun <V1, V2, V3, V4, V5> `when`(futureV1: Future<V1>, futureV2: Future<V2>, futureV3: Future<V3>, futureV4: Future<V4>, futureV5: Future<V5>): Kromise<MultipleResults5<V1, V2, V3, V4, V5>, OneReject<Throwable>, MasterProgress> {
        assertNotNull(futureV1, FUTURE_V1)
        assertNotNull(futureV2, FUTURE_V2)
        assertNotNull(futureV3, FUTURE_V3)
        assertNotNull(futureV4, FUTURE_V4)
        assertNotNull(futureV5, FUTURE_V5)
        return MasterDeferredObject5(`when`(futureV1), `when`(futureV2), `when`(futureV3), `when`(futureV4), `when`(futureV5))
    }

    override fun <V1, V2, V3, V4, V5> `when`(futureV1: Future<V1>, futureV2: Future<V2>, futureV3: Future<V3>, futureV4: Future<V4>, futureV5: Future<V5>, future6: Future<*>, vararg futures: Future<*>): Kromise<MultipleResultsN<V1, V2, V3, V4, V5>, OneReject<Throwable>, MasterProgress> {
        assertNotNull(futureV1, FUTURE_V1)
        assertNotNull(futureV2, FUTURE_V2)
        assertNotNull(futureV3, FUTURE_V3)
        assertNotNull(futureV4, FUTURE_V4)
        assertNotNull(futureV5, FUTURE_V5)
        assertNotNull(future6, "future6")

        val kromise1 = `when`(futureV1)
        val kromise2 = `when`(futureV2)
        val kromise3 = `when`(futureV3)
        val kromise4 = `when`(futureV4)
        val kromise5 = `when`(futureV5)
        val kromise6 = `when`(future6)

        val kromiseN = arrayOfNulls<Kromise<*, *, *>>(futures.size)
        for (i in futures.indices) {
            kromiseN[i] = `when`(futures[i])
        }
        return MasterDeferredObjectN(kromise1, kromise2, kromise3, kromise4, kromise5, kromise6, kromiseN)
    }

    override fun <D, F, P> `when`(kromise: Kromise<D, F, P>): Kromise<D, F, P> {
        assertNotNull(kromise, "kromise")
        return kromise
    }

    override fun <P> `when`(runnable: DeferredRunnable<P>): Kromise<Void, Throwable, P> {
        assertNotNull(runnable, "runnable")
        return `when`<Void, P>(DeferredFutureTask(runnable))
    }

    override fun <D, P> `when`(callable: DeferredCallable<D, P>): Kromise<D, Throwable, P> {
        assertNotNull(callable, "callable")
        return `when`<D, P>(DeferredFutureTask(callable))
    }

    override fun `when`(runnable: Runnable): Kromise<Void, Throwable, Void> {
        assertNotNull(runnable, "runnable")
        return `when`<Void, Void>(DeferredFutureTask(runnable))
    }

    override fun <D> `when`(callable: Callable<D>): Kromise<D, Throwable, Void> {
        assertNotNull(callable, "callable")
        return `when`<D, Void>(DeferredFutureTask(callable))
    }

    override fun <D, P> `when`(task: DeferredFutureTask<D, P>): Kromise<D, Throwable, P> {
        assertNotNull(task, "task")
        if (task.startPolicy === StartPolicy.AUTO || task.startPolicy === StartPolicy.DEFAULT && isAutoSubmit()) {
            submit(task)
        }

        return task.kromise()
    }

    override fun <D> `when`(future: Future<D>): Kromise<D, Throwable, Void> {
        // make sure the task is automatically started
        return `when`(deferredCallableFor(future))
    }

    override fun race(runnableV1: Runnable, runnableV2: Runnable, vararg runnables: Runnable): Kromise<OneResult<*>, OneReject<Throwable>, Void> {
        assertNotNull(runnableV1, RUNNABLE_V1)
        assertNotNull(runnableV2, RUNNABLE_V2)

        val allTasks = arrayOfNulls<DeferredFutureTask<*, *>>(2 + (runnables?.size ?: 0))
        allTasks[0] = DeferredFutureTask<Void, Void>(runnableV1)
        allTasks[1] = DeferredFutureTask<Void, Void>(runnableV2)
        if (runnables != null) {
            for (i in runnables.indices) {
                allTasks[2 + i] = DeferredFutureTask<Void, Void>(runnables[i])
            }
        }

        return submitForSingle(allTasks)
    }

    override fun race(callableV1: Callable<*>, callableV2: Callable<*>, vararg callables: Callable<*>): Kromise<OneResult<*>, OneReject<Throwable>, Void> {
        assertNotNull(callableV1, CALLABLE_V1)
        assertNotNull(callableV2, CALLABLE_V2)

        val allTasks = arrayOfNulls<DeferredFutureTask<*, *>>(2 + (callables?.size ?: 0))
        allTasks[0] = DeferredFutureTask<Any, Void>(callableV1 as Callable<Any>)
        allTasks[1] = DeferredFutureTask<Any, Void>(callableV2 as Callable<Any>)
        if (callables != null) {
            for (i in callables.indices) {
                allTasks[2 + i] = DeferredFutureTask<Any, Void>(callables[i] as Callable<Any>)
            }
        }

        return submitForSingle(allTasks)
    }

    override fun race(runnableV1: DeferredRunnable<*>, runnableV2: DeferredRunnable<*>, vararg runnables: DeferredRunnable<*>): Kromise<OneResult<*>, OneReject<Throwable>, Void> {
        assertNotNull(runnableV1, RUNNABLE_V1)
        assertNotNull(runnableV2, RUNNABLE_V2)

        val allTasks = arrayOfNulls<DeferredFutureTask<*, *>>(2 + (runnables?.size ?: 0))
        allTasks[0] = DeferredFutureTask<Void, Void>(runnableV1)
        allTasks[1] = DeferredFutureTask<Void, Void>(runnableV2)
        if (runnables != null) {
            for (i in runnables.indices) {
                allTasks[2 + i] = DeferredFutureTask<Void, Void>(runnables[i])
            }
        }

        return submitForSingle(allTasks)
    }

    override fun race(callableV1: DeferredCallable<*, *>, callableV2: DeferredCallable<*, *>, vararg callables: DeferredCallable<*, *>): Kromise<OneResult<*>, OneReject<Throwable>, Void> {
        assertNotNull(callableV1, CALLABLE_V1)
        assertNotNull(callableV2, CALLABLE_V2)

        val allTasks = arrayOfNulls<DeferredFutureTask<*, *>>(2 + (callables?.size ?: 0))
        allTasks[0] = DeferredFutureTask(callableV1 as DeferredCallable<Any, Any>)
        allTasks[1] = DeferredFutureTask(callableV2 as DeferredCallable<Any, Any>)
        if (callables != null) {
            for (i in callables.indices) {
                allTasks[2 + i] = DeferredFutureTask(callables[i] as DeferredCallable<Any, Any>)
            }
        }

        return submitForSingle(allTasks)
    }

    override fun race(futureV1: Future<*>, futureV2: Future<*>, vararg futures: Future<*>): Kromise<OneResult<*>, OneReject<Throwable>, Void> {
        assertNotNull(futureV1, FUTURE_V1)
        assertNotNull(futureV2, FUTURE_V2)

        val allTasks = arrayOfNulls<DeferredFutureTask<*, *>>(2 + (futures?.size ?: 0))
        allTasks[0] = DeferredFutureTask(deferredCallableFor<Any>(futureV1))
        allTasks[1] = DeferredFutureTask(deferredCallableFor<Any>(futureV2))
        if (futures != null) {
            for (i in futures.indices) {
                allTasks[2 + i] = DeferredFutureTask(deferredCallableFor<Any>(futures[i]))
            }
        }

        return submitForSingle(allTasks)
    }

    override fun race(taskV1: DeferredFutureTask<*, *>, taskV2: DeferredFutureTask<*, *>, vararg tasks: DeferredFutureTask<*, *>): Kromise<OneResult<*>, OneReject<Throwable>, Void> {
        assertNotNull(taskV1, TASK_V1)
        assertNotNull(taskV2, TASK_V2)

        val allTasks = arrayOfNulls<DeferredFutureTask<*, *>>(2 + (tasks?.size ?: 0))
        allTasks[0] = taskV1
        allTasks[1] = taskV2
        if (tasks != null) {
            for (i in tasks.indices) {
                allTasks[2 + i] = tasks[i]
            }
        }

        return submitForSingle(allTasks)
    }

    protected fun submitForSingle(tasks: Array<DeferredFutureTask<*, *>>): Kromise<OneResult<*>, OneReject<Throwable>, Void> {
        for (task in tasks) {
            submit(task)
        }
        return SingleDeferredObject(tasks)
    }

    protected fun <D> deferredCallableFor(future: Future<D>): DeferredCallable<D, Void> {
        assertNotNull(future, "future")

        return object : DeferredCallable<D, Void>(StartPolicy.AUTO) {
            @Throws(Exception::class)
            override fun call(): D {
                try {
                    return future.get()
                } catch (e: InterruptedException) {
                    throw e
                } catch (e: ExecutionException) {
                    if (e.cause is Exception) {
                        throw e.cause as Exception
                    } else {
                        throw e
                    }
                }

            }
        }
    }

    override fun settle(runnableV1: Runnable, runnableV2: Runnable, vararg runnables: Runnable): Kromise<AllValues, Throwable, MasterProgress> {
        assertNotNull(runnableV1, RUNNABLE_V1)
        assertNotNull(runnableV2, RUNNABLE_V2)

        val kromises = arrayOfNulls<Kromise<*, *, *>>(2 + (runnables?.size ?: 0))
        kromises[0] = `when`(runnableV1)
        kromises[1] = `when`(runnableV2)
        if (runnables != null) {
            for (i in runnables.indices) {
                kromises[2 + i] = `when`(runnables[i])
            }
        }

        return AllValuesDeferredObject(kromises)
    }

    override fun settle(callableV1: Callable<*>, callableV2: Callable<*>, vararg callables: Callable<*>): Kromise<AllValues, Throwable, MasterProgress> {
        assertNotNull(callableV1, CALLABLE_V1)
        assertNotNull(callableV2, CALLABLE_V2)

        val kromises = arrayOfNulls<Kromise<*, *, *>>(2 + (callables?.size ?: 0))
        kromises[0] = `when`(callableV1)
        kromises[1] = `when`(callableV2)
        if (callables != null) {
            for (i in callables.indices) {
                kromises[2 + i] = `when`(callables[i])
            }
        }

        return AllValuesDeferredObject(kromises)
    }

    override fun settle(runnableV1: DeferredRunnable<*>, runnableV2: DeferredRunnable<*>, vararg runnables: DeferredRunnable<*>): Kromise<AllValues, Throwable, MasterProgress> {
        assertNotNull(runnableV1, RUNNABLE_V1)
        assertNotNull(runnableV2, RUNNABLE_V2)

        val kromises = arrayOfNulls<Kromise<*, *, *>>(2 + (runnables?.size ?: 0))
        kromises[0] = `when`(runnableV1)
        kromises[1] = `when`(runnableV2)
        if (runnables != null) {
            for (i in runnables.indices) {
                kromises[2 + i] = `when`(runnables[i])
            }
        }

        return AllValuesDeferredObject(kromises)
    }

    override fun settle(callableV1: DeferredCallable<*, *>, callableV2: DeferredCallable<*, *>, vararg callables: DeferredCallable<*, *>): Kromise<AllValues, Throwable, MasterProgress> {
        assertNotNull(callableV1, CALLABLE_V1)
        assertNotNull(callableV2, CALLABLE_V2)

        val kromises = arrayOfNulls<Kromise<*, *, *>>(2 + (callables?.size ?: 0))
        kromises[0] = `when`(callableV1)
        kromises[1] = `when`(callableV2)
        if (callables != null) {
            for (i in callables.indices) {
                kromises[2 + i] = `when`(callables[i])
            }
        }

        return AllValuesDeferredObject(kromises)
    }

    fun settle(futureV1: Future<*>, futureV2: Future<*>, vararg futures: Future<*>): Kromise<AllValues, Throwable, MasterProgress> {
        assertNotNull(futureV1, FUTURE_V1)
        assertNotNull(futureV2, FUTURE_V2)

        val kromises = arrayOfNulls<Kromise<*, *, *>>(2 + (futures?.size ?: 0))
        kromises[0] = `when`(futureV1)
        kromises[1] = `when`(futureV2)
        if (futures != null) {
            for (i in futures.indices) {
                kromises[2 + i] = `when`(futures[i])
            }
        }

        return AllValuesDeferredObject(kromises)
    }

    override fun settle(taskV1: DeferredFutureTask<*, *>, taskV2: DeferredFutureTask<*, *>, vararg tasks: DeferredFutureTask<*, *>): Kromise<AllValues, Throwable, MasterProgress> {
        assertNotNull(taskV1, TASK_V1)
        assertNotNull(taskV2, TASK_V2)

        val kromises = arrayOfNulls<Kromise<*, *, *>>(2 + (tasks?.size ?: 0))
        kromises[0] = `when`(taskV1)
        kromises[1] = `when`(taskV2)
        if (tasks != null) {
            for (i in tasks.indices) {
                kromises[2 + i] = `when`(tasks[i])
            }
        }

        return AllValuesDeferredObject(kromises)
    }

    fun settle(kromiseV1: Kromise<*, *, *>, kromiseV2: Kromise<*, *, *>, vararg kromises: Kromise<*, *, *>): Kromise<AllValues, Throwable, MasterProgress> {
        assertNotNull(kromiseV1, "kromiseV1")
        assertNotNull(kromiseV2, "kromiseV2")

        val allKromises = arrayOfNulls<Kromise<*, *, *>>(2 + (kromises?.size ?: 0))
        allKromises[0] = kromiseV1
        allKromises[1] = kromiseV2
        if (kromises != null) {
            System.arraycopy(kromises, 0, allKromises, 2, kromises.size)
        }

        return AllValuesDeferredObject(allKromises)
    }

    @Deprecated("")
    protected fun assertNotEmpty(objects: Array<Any>?) {
        if (objects == null || objects.size == 0) {
            throw IllegalArgumentException(
                    "Arguments is null or its length is empty")
        }
    }

    protected fun assertNotNull(`object`: Any?, name: String) {
        if (`object` == null) {
            throw IllegalArgumentException("Argument '$name' must not be null")
        }
    }

    override fun `when`(iterable: Iterable<*>): Kromise<MultipleResults, OneReject<*>, MasterProgress> {
        if (iterable == null) {
            throw IllegalArgumentException("Iterable is null")
        }

        val iterator = iterable.iterator()
        if (!iterator.hasNext()) {
            throw IllegalArgumentException("Iterable is empty")
        }

        val items = LinkedList<Any>()
        val kromises = ArrayList<Kromise<*, *, *>>()

        // First pass, check each element to make sure it can be converted to a kromise
        // This is done in 2 passes because we don't want to submit tasks but also throw an Exception because some
        // object was not able to convert. The method should succeed all or nothing.
        while (iterator.hasNext()) {
            val `object` = iterator.next()
            if (!canKromise(`object`!!)) {
                throw IllegalArgumentException("An item of type " + `object`.javaClass.getName() + " cannot be converted to a Kromise")
            }
            items.add(`object`)
        }

        // Second pass, now we know every object can be converted to a Kromise, convert them
        for (item in items) {
            kromises.add(toKromise(item))
        }
        return MasterDeferredObjectUntypedN(kromises.toTypedArray()).kromise()
    }

    override fun race(iterable: Iterable<*>): Kromise<OneResult<*>, OneReject<Throwable>, Void> {
        if (iterable == null) {
            throw IllegalArgumentException("Iterable is null")
        }

        val iterator = iterable.iterator()
        if (!iterator.hasNext()) {
            throw IllegalArgumentException("Iterable is empty")
        }

        val items = LinkedList<Any>()
        val allTasks = ArrayList<DeferredFutureTask<*, *>>()
        // First pass, check each element to make sure it can be converted to a kromise
        // This is done in 2 passes because we don't want to submit tasks but also throw an Exception because some
        // object was not able to convert. The method should succeed all or nothing.
        while (iterator.hasNext()) {
            val `object` = iterator.next()
            if (!canKromise(`object`!!)) {
                throw IllegalArgumentException("An item of type " + `object`.javaClass.name + " cannot be converted to a DeferredFutureTask")
            }
            // additional check: we can't use Kromise to create a DeferredFutureTask
            if (`object` is Kromise<*, *, *>) {
                throw IllegalArgumentException("An item of type " + `object`.javaClass.name + " cannot be converted to a DeferredFutureTask")
            }
            items.add(`object`)
        }

        // Second pass, now we know every object can be converted to a DeferredFutureTask, convert them
        for (item in items) {
            allTasks.add(toDeferredFutureTask(item))
        }

        return submitForSingle(allTasks.toTypedArray())
    }

    override fun settle(iterable: Iterable<*>): Kromise<AllValues, Throwable, MasterProgress> {
        if (iterable == null) {
            throw IllegalArgumentException("Iterable is null")
        }

        val iterator = iterable.iterator()
        if (!iterator.hasNext()) {
            throw IllegalArgumentException("Iterable is empty")
        }

        val items = LinkedList<Any>()
        val kromises = ArrayList<Kromise<*, *, *>>()

        // First pass, check each element to make sure it can be converted to a kromise
        // This is done in 2 passes because we don't want to submit tasks but also throw an Exception because some
        // object was not able to convert. The method should succeed all or nothing.
        while (iterator.hasNext()) {
            val `object` = iterator.next()
            if (!canKromise(`object`!!)) {
                throw IllegalArgumentException("An item of type " + `object`.javaClass.name + " cannot be converted to a Kromise")
            }
            items.add(`object`)
        }

        // Second pass, now we know every object can be converted to a Kromise, convert them
        for (item in items) {
            kromises.add(toKromise(item))
        }

        return AllValuesDeferredObject(kromises.toTypedArray())
    }

    override fun <D, F, P> resolve(resolve: D): Kromise<D, F, P> {
        return DeferredObject<D, F, P>().resolve(resolve).kromise()
    }

    override fun <D, F, P> reject(reject: F): Kromise<D, F, P> {
        return DeferredObject<D, F, P>().reject(reject).kromise()
    }

    protected fun canKromise(o: Any): Boolean {
        return when (o) {
            is DeferredFutureTask<*, *> -> true
            is DeferredRunnable<*> -> true
            is DeferredCallable<*, *> -> true
            is Runnable -> true
            is Callable<*> -> true
            is Future<*> -> true
            is Kromise<*, *, *> -> true
            else -> false
        }
    }

    protected fun toKromise(o: Any): Kromise<*, *, *> {
        return when (o) {
            is DeferredFutureTask<*, *> -> `when`(o)
            is DeferredRunnable<*> -> `when`(o)
            is DeferredCallable<*, *> -> `when`(o)
            is Runnable -> `when`(o)
            is Callable<*> -> `when`(o)
            is Future<*> -> `when`(o)
            is Kromise<*, *, *> -> o
            else -> throw IllegalStateException("Unable to convert object to Kromise. Should be guarded by canKromise()")
        }
    }

    protected fun toDeferredFutureTask(o: Any): DeferredFutureTask<*, *> {
        return o as? DeferredFutureTask<*, *> ?: if (o is DeferredRunnable<*>) {
            DeferredFutureTask(o)
        } else if (o is DeferredCallable<*, *>) {
            DeferredFutureTask(o)
        } else if (o is Runnable) {
            DeferredFutureTask(o)
        } else if (o is Callable<*>) {
            DeferredFutureTask(o)
        } else if (o is Future<*>) {
            DeferredFutureTask(deferredCallableFor(o))
        } else {
            throw IllegalStateException("Unable to convert object to DeferredFutureTask. Should be guarded by canKromise()")
        }
    }
    
}