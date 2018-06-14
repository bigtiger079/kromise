package com.bigtiger.kromise


interface Kromise<D, F, P> {

    /**
     * @return the state of this kromise.
     */
    fun state(): State

    /**
     * Queries the state of this kromise, returning `true` iff it is `State.PENDING`.
     *
     * @see State.PENDING
     *
     * @return `true` if the current state of this kromise is `State.PENDING`, `false` otherwise.
     */
    fun isPending(): Boolean

    /**
     * Queries the state of this kromise, returning `true` iff it is `State.RESOLVED`.
     *
     * @see State.RESOLVED
     *
     * @return `true` if the current state of this kromise is `State.RESOLVED`, `false` otherwise.
     */
    fun isResolved(): Boolean

    /**
     * Queries the state of this kromise, returning `true` iff it is `State.REJECTED`.
     *
     * @see State.REJECTED
     *
     * @return `true` if the current state of this kromise is `State.REJECTED`, `false` otherwise.
     */
    fun isRejected(): Boolean

    /**
     * Equivalent to [.done]
     *
     * @param doneCallback see [.done]
     * @return `this` for chaining more calls
     */
    fun then(doneCallback: DoneCallback<in D>): Kromise<D, F, P>

//    fun then(doneCallback:(D?)->Unit):Kromise<D, F, P>

    /**
     * Equivalent to [.done].[.fail]
     *
     * @param doneCallback see [.done]
     * @param failCallback see [.fail]
     * @return `this` for chaining more calls
     */
    fun then(doneCallback: DoneCallback<in D>, failCallback: FailCallback<in F>): Kromise<D, F, P>

    /**
     * Equivalent to [.done].[.fail].[.progress]
     *
     * @param doneCallback see [.done]
     * @param failCallback see [.fail]
     * @param progressCallback see [.progress]
     * @return `this` for chaining more calls
     */
    fun then(doneCallback: DoneCallback<in D>,
             failCallback: FailCallback<in F>, progressCallback: ProgressCallback<in P>): Kromise<D, F, P>

    /**
     * Equivalent to `then(doneFilter, null, null)`
     *
     * @see .then
     * @param doneFilter the filter to execute when a result is available
     * @return a new kromise for the filtered result
     */
    fun <D_OUT> then(doneFilter: DoneFilter<in D, out D_OUT>): Kromise<D_OUT, F, P>


    /**
     * Equivalent to `then(doneFilter, failFilter, null)`
     *
     * @see .then
     * @param doneFilter the filter to execute when a result is available
     * @param failFilter the filter to execute when a failure is available
     * @return a new kromise for the filtered result and failure.
     */
    fun <D_OUT, F_OUT> then(
            doneFilter: DoneFilter<in D, out D_OUT>,
            failFilter: FailFilter<in F, out F_OUT>): Kromise<D_OUT, F_OUT, P>

    /**
     * This method will register filters such that when a Deferred object is either
     * resolved ([Deferred.resolve]), rejected ([Deferred.reject]) or
     * is notified of progress ([Deferred.notify]), the corresponding filter
     * will be invoked.  The result of the filter will be used to invoke the same action on the
     * returned kromise.
     *
     * [DoneFilter] and [FailFilter] will be triggered at the time the Deferred object is
     * resolved or rejected.  If the Deferred object is already resolved or rejected the filter is
     * triggered immediately.
     *
     * Filters allow to transform the outcome of a kromise into something else.  This concept is equivalent
     * to the map() method of the java stream API.
     *
     * If any of the filter is not specified (`null`), a default No Op filter is used.
     * If your filter is returning a [Promise] consider using [.then].
     *
     * <pre>
     * `
     * Deferred deferred = new DeferredObject();
     * Promise kromise = deferred.kromise();
     * Promise filtered = kromise.then(new DoneFilter<Integer></Integer>, Integer>() {
     * Integer filterDone(Integer result) {
     * return result * 10;
     * }
     * });
     *
     * filtered.then(new DoneCallback<Integer>() {
     * void onDone(Integer result) {
     * System.out.println(result);
     * }
     * });
     *
     * deferred.resolve(1); // prints 10
    </Integer>` *
    </pre> *
     *
     * @param doneFilter the filter to execute when a result is available.
     * If `null`, use [org.jdeferred2.impl.FilteredPromise.NoOpDoneFilter]
     * @param failFilter the filter to execute when a failure is available.
     * If `null`, use [org.jdeferred2.impl.FilteredPromise.NoOpFailFilter]
     * @param progressFilter the filter to execute when progress info is available.
     * If `null`, use [org.jdeferred2.impl.FilteredPromise.NoOpProgressFilter]
     * @return a new kromise for the filtered result, failure and progress.
     */
    fun <D_OUT, F_OUT, P_OUT> then(
            doneFilter: DoneFilter<in D, out D_OUT>,
            failFilter: FailFilter<in F, out F_OUT>,
            progressFilter: ProgressFilter<in P, out P_OUT>): Kromise<D_OUT, F_OUT, P_OUT>


    fun <D_OUT> then(doneFilter: (D)->D_OUT): Kromise<D_OUT, F, P>
    fun <D_OUT, F_OUT> then(doneFilter: (D)->D_OUT, failFilter:(F)->F_OUT): Kromise<D_OUT, F_OUT, P>
    fun <D_OUT, F_OUT, P_OUT> then(doneFilter: (D)->D_OUT,
                                   failFilter:(F)->F_OUT,
                                   progressFilter: (P)->P_OUT): Kromise<D_OUT, F_OUT, P_OUT>

    /**
     * Equivalent to {#code then(DonePipe, null, null)}
     *
     * @see .then
     * @param donePipe the pipe to invoke when a result is available
     * @return a new kromise for the piped result.
     */
    fun <D_OUT> then(donePipe: DonePipe<in D, out D_OUT, out F, out P>): Kromise<D_OUT, F, P>

    /**
     * Equivalent to `then(DonePipe, FailPipe, null)`
     *
     * @see .then
     * @param donePipe the pipe to invoke when a result is available
     * @param failPipe the pipe to invoke when a failure is available
     * @return a new kromise for the piped result and failure.
     */
    fun <D_OUT, F_OUT> then(
            donePipe: DonePipe<in D, out D_OUT, out F_OUT, out P>,
            failPipe: FailPipe<in F, out D_OUT, out F_OUT, out P>): Kromise<D_OUT, F_OUT, P>

    /**
     * This method will register pipes such that when a Deferred object is either
     * resolved ([Deferred.resolve]), rejected ([Deferred.reject]) or
     * is notified of progress ([Deferred.notify]), the corresponding pipe
     * will be invoked.
     *
     * [DonePipe] and [FailPipe] will be triggered at the time the Deferred object is
     * resolved or rejected.  If the Deferred object is already resolved or rejected the filter is
     * triggered immediately.
     *
     * This method is similar to JQuery's pipe() method, where a new [Promise] is returned
     * by the the pipe filter instead of the original.  This is useful to handle return values
     * and then rewiring it to different callbacks.
     *
     * Pipes start a new [Deferred] object.  This allows to chain asynchronous calls.
     *
     * If your pipe does not do any asynchronous work consider using [.then]
     *
     * <pre>
     * `
     * kromise.then(new DonePipe<Integer></Integer>, Integer, String, Void>() {
     * @Override
     * Deferred<Integer></Integer>, Void, Void> pipeDone(Integer result) {
     * // Reject values greater than 100
     * if (result > 100) {
     * return new DeferredObject<Integer></Integer>, Void, Void>().reject("Failed");
     * } else {
     * return new DeferredObject<Integer></Integer>, Void, Void>().resolve(result);
     * }
     * }
     * }).done(...)
     * .fail(...);
    ` *
    </pre> *
     *
     * @param donePipe the pipe to invoke when a result is available.
     * If `null`, result is piped unchanged
     * @param failPipe the pipe to invoke when a failure is available.
     * If `null`, failure is piped unchanged
     * @param progressPipe the pipe to execute when progress info is available.
     * If `null`, progress is piped unchanged
     * @return a new kromise for the piped result, failure and progress.
     */
    fun <D_OUT, F_OUT, P_OUT> then(
            donePipe: DonePipe<in D, out D_OUT, out F_OUT, out P_OUT>,
            failPipe: FailPipe<in F, out D_OUT, out F_OUT, out P_OUT>,
            progressPipe: ProgressPipe<in P, out D_OUT, out F_OUT, out P_OUT>): Kromise<D_OUT, F_OUT, P_OUT>

    /**
     * This method will register a pipe such that when a Deferred object is either
     * resolved ([Deferred.resolve]) or rejected ([Deferred.reject])
     * the pipe will be invoked.
     *
     * [AlwaysPipe] will be triggered at the time the Deferred object is
     * resolved or rejected.  If the Deferred object is already resolved or rejected the filter is
     * triggered immediately.
     *
     * This method is similar to JQuery's pipe() method, where a new [Promise] is returned
     * by the the pipe filter instead of the original.  This is useful to handle return values
     * and then rewiring it to different callbacks.
     *
     * Pipes start a new [Deferred] object.  This allows to chain asynchronous calls.
     *
     * <pre>
     * `
     * kromise.always(new AlwaysPipe<Integer></Integer>, Integer, String, String, Void>() {
     * @Override
     * Promise<Integer></Integer>, Void, Void> pipeAlways(State state, Integer resolved, Integer rejected) {
     * if (state == State.RESOLVED) {
     * return new DeferredObject<String></String>, String, Void>().resolve("Success");
     * } else {
     * return new DeferredObject<String></String>, String, Void>().reject("Failed");
     * }
     * }
     * }).done(...)
     * .fail(...);
    ` *
    </pre> *
     *
     * @since 2.0
     * @param alwaysPipe the pipe to invoke when a result or failure is available.
     * @return a new kromise for the piped result or failure.
     */
    fun <D_OUT, F_OUT> always(
            alwaysPipe: AlwaysPipe<in D, in F, out D_OUT, out F_OUT, out P>): Kromise<D_OUT, F_OUT, P>

    /**
     * This method will register [DoneCallback] so that when a Deferred object
     * is resolved ([Deferred.resolve]), [DoneCallback] will be triggered.
     * If the Deferred object is already resolved then the [DoneCallback] is triggered immediately.
     *
     * You can register multiple [DoneCallback] by calling the method multiple times.
     * The order of callback trigger is based on the order they have been registered.
     *
     * <pre>
     * `
     * kromise.progress(new DoneCallback(){
     * public void onDone(Object done) {
     * ...
     * }
     * });
    ` *
    </pre> *
     *
     * @see Deferred.resolve
     * @param callback the callback to be triggered
     * @return `this` for chaining more calls
     */
    fun done(callback: DoneCallback<in D>): Kromise<D, F, P>

    fun done(callback:(D?)->Unit): Kromise<D, F, P>

    /**
     * This method will register [FailCallback] so that when a Deferred object
     * is rejected ([Deferred.reject]), [FailCallback] will be triggered.
     * If the Deferred object is already rejected then the [FailCallback] is triggered immediately.
     *
     * You can register multiple [FailCallback] by calling the method multiple times.
     * The order of callback trigger is based on the order they have been registered.
     *
     * <pre>
     * `
     * kromise.fail(new FailCallback(){
     * public void onFail(Object rejection) {
     * ...
     * }
     * });
    ` *
    </pre> *
     *
     * @see Deferred.reject
     * @param callback the callback to be triggered
     * @return `this` for chaining more calls
     */
    fun fail(callback: FailCallback<in F>): Kromise<D, F, P>

    /**
     * This method will register [AlwaysCallback] so that when a Deferred object is either
     * resolved ([Deferred.resolve]) or rejected ([Deferred.reject]),
     * [AlwaysCallback] will be triggered.
     * If the Deferred object is already resolved or rejected then the [AlwaysCallback] is
     * triggered immediately.
     *
     * You can register multiple [AlwaysCallback] by calling the method multiple times.
     * The order of callback trigger is based on the order they have been registered.
     *
     * [AlwaysCallback]s are triggered after any [DoneCallback] or [FailCallback]
     * respectively.
     *
     * <pre>
     * `
     * kromise.always(new AlwaysCallback(){
     * public void onAlways(State state, Object result, Object rejection) {
     * if (state == State.RESOLVED) {
     * // do something with result
     * } else {
     * // do something with rejection
     * }
     * }
     * });
    ` *
    </pre> *
     *
     * @see Deferred.resolve
     * @see Deferred.reject
     * @param callback the callback to be triggered
     * @return `this` for chaining more calls
     */
    fun always(callback: AlwaysCallback<in D, in F>): Kromise<D, F, P>

    /**
     * This method will register [ProgressCallback] so that when a Deferred object
     * is notified of progress ([Deferred.notify]), [ProgressCallback] will be triggered.
     *
     * You can register multiple [ProgressCallback] by calling the method multiple times.
     * The order of callback trigger is based on the order they have been registered.
     *
     * <pre>
     * `
     * kromise.progress(new ProgressCallback(){
     * public void onProgress(Object progress) {
     * // e.g., update progress in the GUI while the background task is still running.
     * }
     * });
    ` *
    </pre> *
     *
     * @see Deferred.notify
     * @param callback the callback to be triggered
     * @return `this` for chaining more calls
     */
    fun progress(callback: ProgressCallback<in P>): Kromise<D, F, P>

    /**
     * This method will wait as long as the State is Pending.  This method will return fast
     * when State is not Pending.
     *
     * @throws InterruptedException if thread is interrupted while waiting
     */
    @Throws(InterruptedException::class)
    fun waitSafely()

    /**
     * This method will wait when the State is Pending, and return when timeout has reached.
     * This method will return fast when State is not Pending.
     *
     * @param timeout the maximum time to wait in milliseconds
     * @throws InterruptedException if thread is interrupted while waiting
     */
    @Throws(InterruptedException::class)
    fun waitSafely(timeout: Long)

}

public enum class State {
    /**
     * The Promise is still pending - it could be created, submitted for execution,
     * or currently running, but not yet finished.
     */
    PENDING,

    /**
     * The Promise has finished running and a failure occurred.
     * Thus, the Promise is rejected.
     *
     * @see Deferred.reject
     */
    REJECTED,

    /**
     * The Promise has finished running successfully.
     * Thus, the Promise is resolved.
     *
     * @see Deferred.resolve
     */
    RESOLVED
}