package com.bigtiger.kromise

import com.bigtiger.kromise.multiple.*
import java.util.concurrent.Callable
import java.util.concurrent.Future

interface DeferredManager {
    /**
     * Simply returns the Kromise.
     *
     * @param Kromise
     *
     * @return Kromise
     */
    fun <D, F, P> `when`(Kromise: Kromise<D, F, P>): Kromise<D, F, P>

    /**
     * Wraps [Runnable] with [DeferredFutureTask].
     *
     * @param runnable
     *
     * @return [.when]
     *
     * @see .when
     */
    fun `when`(runnable: Runnable): Kromise<Void, Throwable, Void>

    /**
     * Wraps [Callable] with [DeferredFutureTask]
     *
     * @param callable
     *
     * @return [.when]
     *
     * @see .when
     */
    fun <D> `when`(callable: Callable<D>): Kromise<D, Throwable, Void>

    /**
     * Wraps [Future] and waits for [Future.get] to return a result
     * in the background.
     *
     * @param future
     *
     * @return [.when]
     */
    fun <D> `when`(future: Future<D>): Kromise<D, Throwable, Void>

    /**
     * Wraps [DeferredRunnable] with [DeferredFutureTask]
     *
     * @param runnable
     *
     * @return [.when]
     *
     * @see .when
     */
    fun <P> `when`(
            runnable: DeferredRunnable<P>): Kromise<Void, Throwable, P>

    /**
     * Wraps [DeferredCallable] with [DeferredFutureTask]
     *
     * @param callable
     *
     * @return [.when]
     *
     * @see .when
     */
    fun <D, P> `when`(
            callable: DeferredCallable<D, P>): Kromise<D, Throwable, P>

    /**
     * May or may not submit [DeferredFutureTask] for execution. See
     * implementation documentation.
     *
     * @param task
     *
     * @return [DeferredFutureTask.Kromise]
     */
    fun <D, P> `when`(task: DeferredFutureTask<D, P>): Kromise<D, Throwable, P>

    /**
     * Submits 2 `Kromise`s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the Kromises rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param KromiseV1 the first Kromise to be resolved
     * @param KromiseV2 the second Kromise to be resolved
     * @param <F>       the common type the Kromises may reject
     * @param <V1>      the resolve type of the first Kromise
     * @param <V2>      the resolve type of the second Kromise
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </V2></V1></F> */
    fun <F, V1, V2> `when`(
            KromiseV1: Kromise<V1, *, *>,
            KromiseV2: Kromise<V2, *, *>): Kromise<MultipleResults2<V1, V2>, OneReject<F>, MasterProgress>

    /**
     * Submits 3 `Kromise`s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the Kromises rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param KromiseV1 the first Kromise to be resolved
     * @param KromiseV2 the second Kromise to be resolved
     * @param KromiseV3 the third Kromise to be resolved
     * @param <F>       the common type the Kromises may reject
     * @param <V1>      the resolve type of the first Kromise
     * @param <V2>      the resolve type of the second Kromise
     * @param <V3>      the resolve type of the third Kromise
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </V3></V2></V1></F> */
    fun <F, V1, V2, V3> `when`(
            KromiseV1: Kromise<V1, *, *>,
            KromiseV2: Kromise<V2, *, *>,
            KromiseV3: Kromise<V3, *, *>): Kromise<MultipleResults3<V1, V2, V3>, OneReject<F>, MasterProgress>

    /**
     * Submits 4 `Kromise`s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the Kromises rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param KromiseV1 the first Kromise to be resolved
     * @param KromiseV2 the second Kromise to be resolved
     * @param KromiseV3 the third Kromise to be resolved
     * @param KromiseV4 the fourth Kromise to be resolved
     * @param <F>       the common type the Kromises may reject
     * @param <V1>      the resolve type of the first Kromise
     * @param <V2>      the resolve type of the second Kromise
     * @param <V3>      the resolve type of the third Kromise
     * @param <V4>      the resolve type of the fourth Kromise
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </V4></V3></V2></V1></F> */
    fun <F, V1, V2, V3, V4> `when`(
            KromiseV1: Kromise<V1, *, *>,
            KromiseV2: Kromise<V2, *, *>,
            KromiseV3: Kromise<V3, *, *>,
            KromiseV4: Kromise<V4, *, *>): Kromise<MultipleResults4<V1, V2, V3, V4>, OneReject<F>, MasterProgress>

    /**
     * Submits 5 `Kromise`s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the Kromises rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param KromiseV1 the first Kromise to be resolved
     * @param KromiseV2 the second Kromise to be resolved
     * @param KromiseV3 the third Kromise to be resolved
     * @param KromiseV4 the fourth Kromise to be resolved
     * @param KromiseV5 the fifth Kromise to be resolved
     * @param <F>       the common type the Kromises may reject
     * @param <V1>      the resolve type of the first Kromise
     * @param <V2>      the resolve type of the second Kromise
     * @param <V3>      the resolve type of the third Kromise
     * @param <V4>      the resolve type of the fourth Kromise
     * @param <V5>      the resolve type of the fifth Kromise
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </V5></V4></V3></V2></V1></F> */
    fun <F, V1, V2, V3, V4, V5> `when`(
            KromiseV1: Kromise<V1, *, *>,
            KromiseV2: Kromise<V2, *, *>,
            KromiseV3: Kromise<V3, *, *>,
            KromiseV4: Kromise<V4, *, *>,
            KromiseV5: Kromise<V5, *, *>): Kromise<MultipleResults5<V1, V2, V3, V4, V5>, OneReject<F>, MasterProgress>

    /**
     * Submits `N` `Kromise`s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the Kromises rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param KromiseV1 the first Kromise to be resolved
     * @param KromiseV2 the second Kromise to be resolved
     * @param KromiseV3 the third Kromise to be resolved
     * @param KromiseV4 the fourth Kromise to be resolved
     * @param KromiseV5 the fifth Kromise to be resolved
     * @param Kromises  additional Kromises to be resolved
     * @param <F>       the common type the Kromises may reject
     * @param <V1>      the resolve type of the first Kromise
     * @param <V2>      the resolve type of the second Kromise
     * @param <V3>      the resolve type of the third Kromise
     * @param <V4>      the resolve type of the fourth Kromise
     * @param <V5>      the resolve type of the fifth Kromise
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </V5></V4></V3></V2></V1></F> */
    fun <F, V1, V2, V3, V4, V5> `when`(
            KromiseV1: Kromise<V1, *, *>,
            KromiseV2: Kromise<V2, *, *>,
            KromiseV3: Kromise<V3, *, *>,
            KromiseV4: Kromise<V4, *, *>,
            KromiseV5: Kromise<V5, *, *>,
            Kromise6: Kromise<*, *, *>,
            vararg Kromises: Kromise<*, *, *>): Kromise<MultipleResultsN<V1, V2, V3, V4, V5>, OneReject<F>, MasterProgress>

    /**
     * Wraps [Runnable] with [DeferredFutureTask]
     *
     * @param runnable1 the first runnable
     * @param runnable2 the second runnable
     * @param runnables additional runnables
     *
     * @see .when
     * @see .when
     * @see .when
     * @see .when
     * @see .when
     */
    fun `when`(
            runnable1: Runnable, runnable2: Runnable, vararg runnables: Runnable): Kromise<MultipleResults, OneReject<Throwable>, MasterProgress>

    /**
     * Submits 2 `Callable`s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the callables rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param callableV1 the first callable to be resolved
     * @param callableV2 the second callable to be resolved
     * @param <V1>       the resolve type of the first callable
     * @param <V2>       the resolve type of the second callable
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </V2></V1> */
    fun <V1, V2> `when`(callableV1: Callable<V1>, callableV2: Callable<V2>):
            Kromise<MultipleResults2<V1, V2>, OneReject<Throwable>, MasterProgress>

    /**
     * Submits 3 `Callable`s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the callables rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param callableV1 the first callable to be resolved
     * @param callableV2 the second callable to be resolved
     * @param callableV3 the third callable to be resolved
     * @param <V1>       the resolve type of the first callable
     * @param <V2>       the resolve type of the second callable
     * @param <V3>       the resolve type of the third callable
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </V3></V2></V1> */
    fun <V1, V2, V3> `when`(
            callableV1: Callable<V1>,
            callableV2: Callable<V2>,
            callableV3: Callable<V3>): Kromise<MultipleResults3<V1, V2, V3>, OneReject<Throwable>, MasterProgress>

    /**
     * Submits 4 `Callable`s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the callables rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param callableV1 the first callable to be resolved
     * @param callableV2 the second callable to be resolved
     * @param callableV3 the third callable to be resolved
     * @param callableV4 the fourth callable to be resolved
     * @param <V1>       the resolve type of the first callable
     * @param <V2>       the resolve type of the second callable
     * @param <V3>       the resolve type of the third callable
     * @param <V4>       the resolve type of the fourth callable
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </V4></V3></V2></V1> */
    fun <V1, V2, V3, V4> `when`(
            callableV1: Callable<V1>,
            callableV2: Callable<V2>,
            callableV3: Callable<V3>,
            callableV4: Callable<V4>): Kromise<MultipleResults4<V1, V2, V3, V4>, OneReject<Throwable>, MasterProgress>

    /**
     * Submits 5 `Callable`s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the callables rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param callableV1 the first callable to be resolved
     * @param callableV2 the second callable to be resolved
     * @param callableV3 the third callable to be resolved
     * @param callableV4 the fourth callable to be resolved
     * @param callableV5 the fifth callable to be resolved
     * @param <V1>       the resolve type of the first callable
     * @param <V2>       the resolve type of the second callable
     * @param <V3>       the resolve type of the third callable
     * @param <V4>       the resolve type of the fourth callable
     * @param <V5>       the resolve type of the fifth callable
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </V5></V4></V3></V2></V1> */
    fun <V1, V2, V3, V4, V5> `when`(
            callableV1: Callable<V1>,
            callableV2: Callable<V2>,
            callableV3: Callable<V3>,
            callableV4: Callable<V4>,
            callableV5: Callable<V5>): Kromise<MultipleResults5<V1, V2, V3, V4, V5>, OneReject<Throwable>, MasterProgress>

    /**
     * Submits `N` `Callable`s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the callables rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param callableV1 the first callable to be resolved
     * @param callableV2 the second callable to be resolved
     * @param callableV3 the third callable to be resolved
     * @param callableV4 the fourth callable to be resolved
     * @param callableV5 the fifth callable to be resolved
     * @param callables  additional callables to be resolved
     * @param <V1>       the resolve type of the first callable
     * @param <V2>       the resolve type of the second callable
     * @param <V3>       the resolve type of the third callable
     * @param <V4>       the resolve type of the fourth callable
     * @param <V5>       the resolve type of the fifth callable
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </V5></V4></V3></V2></V1> */
    fun <V1, V2, V3, V4, V5> `when`(
            callableV1: Callable<V1>,
            callableV2: Callable<V2>,
            callableV3: Callable<V3>,
            callableV4: Callable<V4>,
            callableV5: Callable<V5>,
            callable6: Callable<*>,
            vararg callables: Callable<*>): Kromise<MultipleResultsN<V1, V2, V3, V4, V5>, OneReject<Throwable>, MasterProgress>

    /**
     * Submits 2 [DeferredRunnable]s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the runnables rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param runnableP1 the first runnable to be resolved
     * @param runnableP2 the second runnable to be resolved
     * @param <P1>       the progress type of the first runnable
     * @param <P2>       the progress type of the second runnable
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </P2></P1> */
    fun <P1, P2> `when`(
            runnableP1: DeferredRunnable<P1>,
            runnableP2: DeferredRunnable<P2>): Kromise<MultipleResults2<Void, Void>, OneReject<Throwable>, MasterProgress>

    /**
     * Submits 3 [DeferredRunnable]s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the runnables rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param runnableP1 the first runnable to be resolved
     * @param runnableP2 the second runnable to be resolved
     * @param runnableP3 the third runnable to be resolved
     * @param <P1>       the progress type of the first runnable
     * @param <P2>       the progress type of the second runnable
     * @param <P3>       the progress type of the third runnable
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </P3></P2></P1> */
    fun <P1, P2, P3> `when`(
            runnableP1: DeferredRunnable<P1>,
            runnableP2: DeferredRunnable<P2>,
            runnableP3: DeferredRunnable<P3>): Kromise<MultipleResults3<Void, Void, Void>, OneReject<Throwable>, MasterProgress>

    /**
     * Submits 4 [DeferredRunnable]s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the runnables rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param runnableP1 the first runnable to be resolved
     * @param runnableP2 the second runnable to be resolved
     * @param runnableP3 the third runnable to be resolved
     * @param runnableP4 the fourth runnable to be resolved
     * @param <P1>       the progress type of the first runnable
     * @param <P2>       the progress type of the second runnable
     * @param <P3>       the progress type of the third runnable
     * @param <P4>       the progress type of the fourth runnable
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </P4></P3></P2></P1> */
    fun <P1, P2, P3, P4> `when`(
            runnableP1: DeferredRunnable<P1>,
            runnableP2: DeferredRunnable<P2>,
            runnableP3: DeferredRunnable<P3>,
            runnableP4: DeferredRunnable<P4>): Kromise<MultipleResults4<Void, Void, Void, Void>, OneReject<Throwable>, MasterProgress>

    /**
     * Submits 5 [DeferredRunnable]s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the runnables rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param runnableP1 the first runnable to be resolved
     * @param runnableP2 the second runnable to be resolved
     * @param runnableP3 the third runnable to be resolved
     * @param runnableP4 the fourth runnable to be resolved
     * @param runnableP5 the fifth runnable to be resolved
     * @param <P1>       the progress type of the first runnable
     * @param <P2>       the progress type of the second runnable
     * @param <P3>       the progress type of the third runnable
     * @param <P4>       the progress type of the fourth runnable
     * @param <P5>       the progress type of the fifth runnable
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </P5></P4></P3></P2></P1> */
    fun <P1, P2, P3, P4, P5> `when`(
            runnableP1: DeferredRunnable<P1>,
            runnableP2: DeferredRunnable<P2>,
            runnableP3: DeferredRunnable<P3>,
            runnableP4: DeferredRunnable<P4>,
            runnableP5: DeferredRunnable<P5>): Kromise<MultipleResults5<Void, Void, Void, Void, Void>, OneReject<Throwable>, MasterProgress>

    /**
     * Submits `N` [DeferredRunnable]s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the runnables rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param runnableP1 the first runnable to be resolved
     * @param runnableP2 the second runnable to be resolved
     * @param runnableP3 the third runnable to be resolved
     * @param runnableP4 the fourth runnable to be resolved
     * @param runnableP5 the fifth runnable to be resolved
     * @param runnables  additional runnables to be resolved
     * @param <P1>       the progress type of the first runnable
     * @param <P2>       the progress type of the second runnable
     * @param <P3>       the progress type of the third runnable
     * @param <P4>       the progress type of the fourth runnable
     * @param <P5>       the progress type of the fifth runnable
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </P5></P4></P3></P2></P1> */
    fun <P1, P2, P3, P4, P5> `when`(
            runnableP1: DeferredRunnable<P1>,
            runnableP2: DeferredRunnable<P2>,
            runnableP3: DeferredRunnable<P3>,
            runnableP4: DeferredRunnable<P4>,
            runnableP5: DeferredRunnable<P5>,
            runnable6: DeferredRunnable<*>,
            vararg runnables: DeferredRunnable<*>): Kromise<MultipleResultsN<Void, Void, Void, Void, Void>, OneReject<Throwable>, MasterProgress>

    /**
     * Submits 2 [DeferredCallable]s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the callables rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param callableV1 the first callable to be resolved
     * @param callableV2 the second callable to be resolved
     * @param <V1>       the resolve type of the first callable
     * @param <V2>       the resolve type of the second callable
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </V2></V1> */
    fun <V1, V2> `when`(
            callableV1: DeferredCallable<V1, *>,
            callableV2: DeferredCallable<V2, *>): Kromise<MultipleResults2<V1, V2>, OneReject<Throwable>, MasterProgress>

    /**
     * Submits 3 [DeferredCallable]s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the callables rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param callableV1 the first callable to be resolved
     * @param callableV2 the second callable to be resolved
     * @param callableV3 the third callable to be resolved
     * @param <V1>       the resolve type of the first callable
     * @param <V2>       the resolve type of the second callable
     * @param <V3>       the resolve type of the third callable
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </V3></V2></V1> */
    fun <V1, V2, V3> `when`(
            callableV1: DeferredCallable<V1, *>,
            callableV2: DeferredCallable<V2, *>,
            callableV3: DeferredCallable<V3, *>): Kromise<MultipleResults3<V1, V2, V3>, OneReject<Throwable>, MasterProgress>

    /**
     * Submits 4 [DeferredCallable]s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the callables rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param callableV1 the first callable to be resolved
     * @param callableV2 the second callable to be resolved
     * @param callableV3 the third callable to be resolved
     * @param callableV4 the fourth callable to be resolved
     * @param <V1>       the resolve type of the first callable
     * @param <V2>       the resolve type of the second callable
     * @param <V3>       the resolve type of the third callable
     * @param <V4>       the resolve type of the fourth callable
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </V4></V3></V2></V1> */
    fun <V1, V2, V3, V4> `when`(
            callableV1: DeferredCallable<V1, *>,
            callableV2: DeferredCallable<V2, *>,
            callableV3: DeferredCallable<V3, *>,
            callableV4: DeferredCallable<V4, *>): Kromise<MultipleResults4<V1, V2, V3, V4>, OneReject<Throwable>, MasterProgress>

    /**
     * Submits 5 [DeferredCallable]s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the callables rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param callableV1 the first callable to be resolved
     * @param callableV2 the second callable to be resolved
     * @param callableV3 the third callable to be resolved
     * @param callableV4 the fourth callable to be resolved
     * @param callableV5 the fifth callable to be resolved
     * @param <V1>       the resolve type of the first callable
     * @param <V2>       the resolve type of the second callable
     * @param <V3>       the resolve type of the third callable
     * @param <V4>       the resolve type of the fourth callable
     * @param <V5>       the resolve type of the fifth callable
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </V5></V4></V3></V2></V1> */
    fun <V1, V2, V3, V4, V5> `when`(
            callableV1: DeferredCallable<V1, *>,
            callableV2: DeferredCallable<V2, *>,
            callableV3: DeferredCallable<V3, *>,
            callableV4: DeferredCallable<V4, *>,
            callableV5: DeferredCallable<V5, *>): Kromise<MultipleResults5<V1, V2, V3, V4, V5>, OneReject<Throwable>, MasterProgress>

    /**
     * Submits `N` [DeferredCallable]s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the callables rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param callableV1 the first callable to be resolved
     * @param callableV2 the second callable to be resolved
     * @param callableV3 the third callable to be resolved
     * @param callableV4 the fourth callable to be resolved
     * @param callableV5 the fifth callable to be resolved
     * @param callables  additional callables to be resolved
     * @param <V1>       the resolve type of the first callable
     * @param <V2>       the resolve type of the second callable
     * @param <V3>       the resolve type of the third callable
     * @param <V4>       the resolve type of the fourth callable
     * @param <V5>       the resolve type of the fifth callable
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </V5></V4></V3></V2></V1> */
    fun <V1, V2, V3, V4, V5> `when`(
            callableV1: DeferredCallable<V1, *>,
            callableV2: DeferredCallable<V2, *>,
            callableV3: DeferredCallable<V3, *>,
            callableV4: DeferredCallable<V4, *>,
            callableV5: DeferredCallable<V5, *>,
            callable6: DeferredCallable<*, *>,
            vararg callables: DeferredCallable<*, *>): Kromise<MultipleResultsN<V1, V2, V3, V4, V5>, OneReject<Throwable>, MasterProgress>

    /**
     * Submits 2 [DeferredFutureTask]s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the tasks rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param taskV1 the first task to be resolved
     * @param taskV2 the second task to be resolved
     * @param <V1>   the resolve type of the first task
     * @param <V2>   the resolve type of the second task
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </V2></V1> */
    fun <V1, V2> `when`(
            taskV1: DeferredFutureTask<V1, *>,
            taskV2: DeferredFutureTask<V2, *>): Kromise<MultipleResults2<V1, V2>, OneReject<Throwable>, MasterProgress>

    /**
     * Submits 3 [DeferredFutureTask]s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the tasks rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param taskV1 the first task to be resolved
     * @param taskV2 the second task to be resolved
     * @param taskV3 the third task to be resolved
     * @param <V1>   the resolve type of the first task
     * @param <V2>   the resolve type of the second task
     * @param <V3>   the resolve type of the third task
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </V3></V2></V1> */
    fun <V1, V2, V3> `when`(
            taskV1: DeferredFutureTask<V1, *>,
            taskV2: DeferredFutureTask<V2, *>,
            taskV3: DeferredFutureTask<V3, *>): Kromise<MultipleResults3<V1, V2, V3>, OneReject<Throwable>, MasterProgress>

    /**
     * Submits 4 [DeferredFutureTask]s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the tasks rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param taskV1 the first task to be resolved
     * @param taskV2 the second task to be resolved
     * @param taskV3 the third task to be resolved
     * @param taskV4 the fourth task to be resolved
     * @param <V1>   the resolve type of the first task
     * @param <V2>   the resolve type of the second task
     * @param <V3>   the resolve type of the third task
     * @param <V4>   the resolve type of the fourth task
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </V4></V3></V2></V1> */
    fun <V1, V2, V3, V4> `when`(
            taskV1: DeferredFutureTask<V1, *>,
            taskV2: DeferredFutureTask<V2, *>,
            taskV3: DeferredFutureTask<V3, *>,
            taskV4: DeferredFutureTask<V4, *>): Kromise<MultipleResults4<V1, V2, V3, V4>, OneReject<Throwable>, MasterProgress>

    /**
     * Submits 5 [DeferredFutureTask]s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the tasks rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param taskV1 the first task to be resolved
     * @param taskV2 the second task to be resolved
     * @param taskV3 the third task to be resolved
     * @param taskV4 the fourth task to be resolved
     * @param taskV5 the fifth task to be resolved
     * @param <V1>   the resolve type of the first task
     * @param <V2>   the resolve type of the second task
     * @param <V3>   the resolve type of the third task
     * @param <V4>   the resolve type of the fourth task
     * @param <V5>   the resolve type of the fifth task
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </V5></V4></V3></V2></V1> */
    fun <V1, V2, V3, V4, V5> `when`(
            taskV1: DeferredFutureTask<V1, *>,
            taskV2: DeferredFutureTask<V2, *>,
            taskV3: DeferredFutureTask<V3, *>,
            taskV4: DeferredFutureTask<V4, *>,
            taskV5: DeferredFutureTask<V5, *>): Kromise<MultipleResults5<V1, V2, V3, V4, V5>, OneReject<Throwable>, MasterProgress>

    /**
     * Submits `N` [DeferredFutureTask]s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the tasks rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param taskV1 the first task to be resolved
     * @param taskV2 the second task to be resolved
     * @param taskV3 the third task to be resolved
     * @param taskV4 the fourth task to be resolved
     * @param taskV5 the fifth task to be resolved
     * @param tasks  additional tasks to be resolved
     * @param <V1>   the resolve type of the first task
     * @param <V2>   the resolve type of the second task
     * @param <V3>   the resolve type of the third task
     * @param <V4>   the resolve type of the fourth task
     * @param <V5>   the resolve type of the fifth task
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </V5></V4></V3></V2></V1> */
    fun <V1, V2, V3, V4, V5> `when`(
            taskV1: DeferredFutureTask<V1, *>,
            taskV2: DeferredFutureTask<V2, *>,
            taskV3: DeferredFutureTask<V3, *>,
            taskV4: DeferredFutureTask<V4, *>,
            taskV5: DeferredFutureTask<V5, *>,
            task6: DeferredFutureTask<*, *>,
            vararg tasks: DeferredFutureTask<*, *>): Kromise<MultipleResultsN<V1, V2, V3, V4, V5>, OneReject<Throwable>, MasterProgress>

    /**
     * Submits 2 `Future`s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the futures rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param futureV1 the first future to be resolved
     * @param futureV2 the second future to be resolved
     * @param <V1>     the resolve type of the first future
     * @param <V2>     the resolve type of the second future
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </V2></V1> */
    fun <V1, V2> `when`(
            futureV1: Future<V1>,
            futureV2: Future<V2>): Kromise<MultipleResults2<V1, V2>, OneReject<Throwable>, MasterProgress>

    /**
     * Submits 3 `Future`s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the futures rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param futureV1 the first future to be resolved
     * @param futureV2 the second future to be resolved
     * @param futureV3 the third future to be resolved
     * @param <V1>     the resolve type of the first future
     * @param <V2>     the resolve type of the second future
     * @param <V3>     the resolve type of the third future
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </V3></V2></V1> */
    fun <V1, V2, V3> `when`(
            futureV1: Future<V1>,
            futureV2: Future<V2>,
            futureV3: Future<V3>): Kromise<MultipleResults3<V1, V2, V3>, OneReject<Throwable>, MasterProgress>

    /**
     * Submits 4 `Future`s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the futures rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param futureV1 the first future to be resolved
     * @param futureV2 the second future to be resolved
     * @param futureV3 the third future to be resolved
     * @param futureV4 the fourth future to be resolved
     * @param <V1>     the resolve type of the first future
     * @param <V2>     the resolve type of the second future
     * @param <V3>     the resolve type of the third future
     * @param <V4>     the resolve type of the fourth future
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </V4></V3></V2></V1> */
    fun <V1, V2, V3, V4> `when`(
            futureV1: Future<V1>,
            futureV2: Future<V2>,
            futureV3: Future<V3>,
            futureV4: Future<V4>): Kromise<MultipleResults4<V1, V2, V3, V4>, OneReject<Throwable>, MasterProgress>

    /**
     * Submits 5 `Future`s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the futures rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param futureV1 the first future to be resolved
     * @param futureV2 the second future to be resolved
     * @param futureV3 the third future to be resolved
     * @param futureV4 the fourth future to be resolved
     * @param futureV5 the fifth future to be resolved
     * @param <V1>     the resolve type of the first future
     * @param <V2>     the resolve type of the second future
     * @param <V3>     the resolve type of the third future
     * @param <V4>     the resolve type of the fourth future
     * @param <V5>     the resolve type of the fifth future
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </V5></V4></V3></V2></V1> */
    fun <V1, V2, V3, V4, V5> `when`(
            futureV1: Future<V1>,
            futureV2: Future<V2>,
            futureV3: Future<V3>,
            futureV4: Future<V4>,
            futureV5: Future<V5>): Kromise<MultipleResults5<V1, V2, V3, V4, V5>, OneReject<Throwable>, MasterProgress>

    /**
     * Submits `N` `Future`s returns a combined [Kromise].
     * The combined Kromise signals `fail` as soon as any of the futures rejects its value.
     * The return type of the combined [Kromise] contains all resolved values.
     *
     * @param futureV1 the first future to be resolved
     * @param futureV2 the second future to be resolved
     * @param futureV3 the third future to be resolved
     * @param futureV4 the fourth future to be resolved
     * @param futureV5 the fifth future to be resolved
     * @param futures  additional futures to be resolved
     * @param <V1>     the resolve type of the first future
     * @param <V2>     the resolve type of the second future
     * @param <V3>     the resolve type of the third future
     * @param <V4>     the resolve type of the fourth future
     * @param <V5>     the resolve type of the fifth future
     *
     * @return a combined [Kromise]
     *
     * @since 2.0
    </V5></V4></V3></V2></V1> */
    fun <V1, V2, V3, V4, V5> `when`(
            futureV1: Future<V1>,
            futureV2: Future<V2>,
            futureV3: Future<V3>,
            futureV4: Future<V4>,
            futureV5: Future<V5>,
            future6: Future<*>,
            vararg futures: Future<*>): Kromise<MultipleResultsN<V1, V2, V3, V4, V5>, OneReject<Throwable>, MasterProgress>

    /**
     * Creates a [Kromise] that signals `done` or `reject` when the first runnable does so.
     * Wraps each `runnable` with [DeferredFutureTask].
     *
     * @param runnableV1 a task to be executed. Must not be null
     * @param runnableV2 a task to be executed. Must not be null
     * @param runnables  additional tasks to be executed. May be null
     *
     * @return a composite [Kromise] that resolves/rejects as soon as the first of the submitted tasks is resolved/rejected.
     *
     * @since 2.0
     */
    fun race(
            runnableV1: Runnable,
            runnableV2: Runnable,
            vararg runnables: Runnable): Kromise<OneResult<*>, OneReject<Throwable>, Void>

    /**
     * Creates a [Kromise] that signals `done` or `reject` when the first callable does so.
     * Wraps each `callable` with [DeferredFutureTask].
     *
     * @param callableV1 a task to be executed. Must not be null
     * @param callableV2 a task to be executed. Must not be null
     * @param callables  additional tasks to be executed. May be null
     *
     * @return a composite [Kromise] that resolves/rejects as soon as the first of the submitted tasks is resolved/rejected.
     *
     * @since 2.0
     */
    fun race(
            callableV1: Callable<*>,
            callableV2: Callable<*>,
            vararg callables: Callable<*>): Kromise<OneResult<*>, OneReject<Throwable>, Void>

    /**
     * Creates a [Kromise] that signals `done` or `reject` when the first runnable does so.
     * Wraps each `runnable` with [DeferredFutureTask].
     *
     * @param runnableV1 a task to be executed. Must not be null
     * @param runnableV2 a task to be executed. Must not be null
     * @param runnables  additional tasks to be executed. May be null
     *
     * @return a composite [Kromise] that resolves/rejects as soon as the first of the submitted tasks is resolved/rejected.
     *
     * @since 2.0
     */
    fun race(
            runnableV1: DeferredRunnable<*>,
            runnableV2: DeferredRunnable<*>,
            vararg runnables: DeferredRunnable<*>): Kromise<OneResult<*>, OneReject<Throwable>, Void>

    /**
     * Creates a [Kromise] that signals `done` or `reject` when the first callable does so.
     * Wraps each `callable` with [DeferredFutureTask].
     *
     * @param callableV1 a task to be executed. Must not be null
     * @param callableV2 a task to be executed. Must not be null
     * @param callables  additional tasks to be executed. May be null
     *
     * @return a composite [Kromise] that resolves/rejects as soon as the first of the submitted tasks is resolved/rejected.
     *
     * @since 2.0
     */
    fun race(
            callableV1: DeferredCallable<*, *>,
            callableV2: DeferredCallable<*, *>,
            vararg callables: DeferredCallable<*, *>): Kromise<OneResult<*>, OneReject<Throwable>, Void>

    /**
     * Creates a [Kromise] that signals `done` or `reject` when the first future does so.
     * Wraps each `future` with [DeferredFutureTask].
     *
     * @param futureV1 a task to be executed. Must not be null
     * @param futureV2 a task to be executed. Must not be null
     * @param futures  additional tasks to be executed. May be null
     *
     * @return a composite [Kromise] that resolves/rejects as soon as the first of the submitted tasks is resolved/rejected.
     *
     * @since 2.0
     */
    fun race(
            futureV1: Future<*>,
            futureV2: Future<*>,
            vararg futures: Future<*>): Kromise<OneResult<*>, OneReject<Throwable>, Void>

    /**
     * Creates a [Kromise] that signals `done` or `reject` when the first task does so.
     *
     * @param taskV1 a task to be executed. Must not be null
     * @param taskV2 a task to be executed. Must not be null
     * @param tasks  additional tasks to be executed. May be null
     *
     * @return a composite [Kromise] that resolves/rejects as soon as the first of the submitted tasks is resolved/rejected.
     *
     * @since 2.0
     */
    fun race(
            taskV1: DeferredFutureTask<*, *>,
            taskV2: DeferredFutureTask<*, *>,
            vararg tasks: DeferredFutureTask<*, *>): Kromise<OneResult<*>, OneReject<Throwable>, Void>

    /**
     * Creates a [Kromise] that signals `done` or `reject` when each runnable does so.
     * Wraps each `runnable` with [DeferredFutureTask].
     *
     * @param runnableV1 a task to be executed. Must not be null
     * @param runnableV2 a task to be executed. Must not be null
     * @param runnables  additional tasks to be executed. May be null
     *
     * @return a composite [Kromise] that collects resolve/reject values from all tasks.
     *
     * @since 2.0
     */
    fun settle(
            runnableV1: Runnable,
            runnableV2: Runnable,
            vararg runnables: Runnable): Kromise<AllValues, Throwable, MasterProgress>

    /**
     * Creates a [Kromise] that signals `done` or `reject` when each callable does so.
     * Wraps each `callable` with [DeferredFutureTask].
     *
     * @param callableV1 a task to be executed. Must not be null
     * @param callableV2 a task to be executed. Must not be null
     * @param callables  additional tasks to be executed. May be null
     *
     * @return a composite [Kromise] that collects resolve/reject values from all tasks.
     *
     * @since 2.0
     */
    fun settle(
            callableV1: Callable<*>,
            callableV2: Callable<*>,
            vararg callables: Callable<*>): Kromise<AllValues, Throwable, MasterProgress>

    /**
     * Creates a [Kromise] that signals `done` or `reject` when each runnable does so.
     * Wraps each `runnable` with [DeferredFutureTask].
     *
     * @param runnableV1 a task to be executed. Must not be null
     * @param runnableV2 a task to be executed. Must not be null
     * @param runnables  additional tasks to be executed. May be null
     *
     * @return a composite [Kromise] that collects resolve/reject values from all tasks.
     *
     * @since 2.0
     */
    fun settle(
            runnableV1: DeferredRunnable<*>,
            runnableV2: DeferredRunnable<*>,
            vararg runnables: DeferredRunnable<*>): Kromise<AllValues, Throwable, MasterProgress>

    /**
     * Creates a [Kromise] that signals `done` or `reject` when each callable does so.
     * Wraps each `callable` with [DeferredFutureTask].
     *
     * @param callableV1 a task to be executed. Must not be null
     * @param callableV2 a task to be executed. Must not be null
     * @param callables  additional tasks to be executed. May be null
     *
     * @return a composite [Kromise] that collects resolve/reject values from all tasks.
     *
     * @since 2.0
     */
    fun settle(
            callableV1: DeferredCallable<*, *>,
            callableV2: DeferredCallable<*, *>,
            vararg callables: DeferredCallable<*, *>): Kromise<AllValues, Throwable, MasterProgress>

    /**
     * Creates a [Kromise] that signals `done` or `reject` when each future does so.
     * Wraps each `future` with [DeferredFutureTask].
     *
     * @param futureV1 a task to be executed. Must not be null
     * @param futureV2 a task to be executed. Must not be null
     * @param futures  additional tasks to be executed. May be null
     *
     * @return a composite [Kromise] that collects resolve/reject values from all tasks.
     *
     * @since 2.0
     */
    fun settle(
            futureV1: Future<*>,
            futureV2: Future<*>,
            vararg futures: Future<*>): Kromise<AllValues, Throwable, MasterProgress>

    /**
     * Creates a [Kromise] that signals `done` or `reject` when each task does so.
     *
     * @param taskV1 a task to be executed. Must not be null
     * @param taskV2 a task to be executed. Must not be null
     * @param tasks  additional tasks to be executed. May be null
     *
     * @return a composite [Kromise] that collects resolve/reject values from all tasks.
     *
     * @since 2.0
     */
    fun settle(
            taskV1: DeferredFutureTask<*, *>,
            taskV2: DeferredFutureTask<*, *>,
            vararg tasks: DeferredFutureTask<*, *>): Kromise<AllValues, Throwable, MasterProgress>

    /**
     * Creates a [Kromise] that signals `done` or `reject` when each Kromise does so.
     *
     * @param KromiseV1 a Kromise. Must not be null
     * @param KromiseV2 a Kromise. Must not be null
     * @param Kromises  additional Kromises. May be null
     *
     * @return a composite [Kromise] that collects resolve/reject values from all Kromises.
     *
     * @since 2.0
     */
    fun settle(
            KromiseV1: Kromise<*, *, *>,
            KromiseV2: Kromise<*, *, *>,
            vararg Kromises: Kromise<*, *, *>): Kromise<AllValues, Throwable, MasterProgress>

    /**
     * Accept an iterable of a variety of different object types, and convert it into corresponding Kromise. E.g.,
     * if an item is a [Callable], it'll call [.when] to convert that into a Kromise.
     *
     *
     * If the item is of an unknown type, it'll throw an [IllegalArgumentException].
     *
     * @param iterable the source of tasks. Must be non-null and not empty. Every item must be convertible to [Kromise]
     *
     * @return a composite [Kromise] that rejects as soon as the first of the submitted tasks is rejected or contains
     * the resolution of all given tasks.
     *
     * @throws IllegalArgumentException if any item in iterable cannot be converted to a [Kromise]
     * @since 2.0
     */
    fun `when`(iterable: Iterable<*>): Kromise<MultipleResults, OneReject<*>, MasterProgress>

    /**
     * Creates a [Kromise] that signals `done` or `reject` when the first task does so.
     * If an item is a [Callable], it'll call [.when] to convert that into a Kromise.
     *
     *
     * If the item is of an unknown type, it'll throw an [IllegalArgumentException].
     * **WARNING: **does not accept items of type `Kromise`.
     *
     * @param iterable the source of tasks. Must be non-null and not empty. Every item must be convertible to [Kromise]
     *
     * @return a composite [Kromise] that resolves/rejects as soon as the first of the submitted tasks is resolved/rejected.
     *
     * @throws IllegalArgumentException if any item in iterable cannot be converted to a [Kromise]
     * @since 2.0
     */
    fun race(iterable: Iterable<*>): Kromise<OneResult<*>, OneReject<Throwable>, Void>

    /**
     * Creates a [Kromise] that signals `done` or `reject` when each task does so.
     * If an item is a [Callable], it'll call [.when] to convert that into a Kromise.
     *
     *
     * If the item is of an unknown type, it'll throw an [IllegalArgumentException].
     *
     * @param iterable the source of tasks. Must be non-null and not empty. Every item must be convertible to [Kromise]
     *
     * @return a composite [Kromise] that collects resolve/reject values from all Kromises.
     *
     * @throws IllegalArgumentException if any item in iterable cannot be converted to a [Kromise]
     * @since 2.0
     */
    fun settle(iterable: Iterable<*>): Kromise<AllValues, Throwable, MasterProgress>

    /**
     * A convenience method create a [Kromise] that immediately resolves to a value.
     *
     * @param resolve value to resolve to
     *
     * @return a Kromise that resolves to value
     *
     * @since 2.0
     */
    fun <D, F, P> resolve(resolve: D): Kromise<D, F, P>

    /**
     * A convenience method to create a [Kromise] that immediately fails with a reason.
     *
     * @param reject reason to reject
     *
     * @return a [Kromise] that rejects with reason
     *
     * @since 2.0
     */
    fun <D, F, P> reject(reject: F): Kromise<D, F, P>
}

enum class StartPolicy {
    /**
     * Let Deferred Manager to determine whether to start the task at its own
     * discretion.
     */
    DEFAULT,

    /**
     * Tells Deferred Manager to automatically start the task
     */
    AUTO,

    /**
     * Tells Deferred Manager that this task will be manually started
     */
    MANUAL
}