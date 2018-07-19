package com.bigtiger.kromise


interface Deferred<D, F>: Kromise<D, F>{
    /**
     * This should be called when a task has completed successfully.
     *
     *
     * <pre>
     * `
     * [Deferred] deferredObject = new [DeferredObject]();
     * [Promise] kromise = deferredObject.kromise();
     * kromise.done(new [DoneCallback]() {
     * public void onDone(Object result) {
     * // Done!
     * }
     * });
     *
     * // another thread using the same deferredObject
     * deferredObject.resolve("OK");
     *
    ` *
    </pre> *
     *
     * @param resolve the resolved value for this `Deferred`
     *
     * @return the reference to this `Deferred` instance.
     */
    fun resolve(resolve: D): Deferred<D, F>

    /**
     * This should be called when a task has completed unsuccessfully,
     * i.e., a failure may have occurred.
     *
     *
     * <pre>
     * `
     * [Deferred] deferredObject = new [DeferredObject]();
     * [Promise] kromise = deferredObject.kromise();
     * kromise.fail(new [FailCallback]() {
     * public void onFail(Object result) {
     * // Failed :(
     * }
     * });
     *
     * // another thread using the same deferredObject
     * deferredObject.reject("BAD");
     *
    ` *
    </pre> *
     *
     * @param reject the rejected value for this `Deferred`
     *
     * @return the reference to this `Deferred` instance.
     */
    fun reject(reject: F): Deferred<D, F>


    /**
     * Return an [Promise] instance (i.e., an observer).  You can register callbacks in this observer.
     *
     * @return the reference to this `Deferred` instance as a `Promise`,
     */
    fun kromise(): Kromise<D, F>
}