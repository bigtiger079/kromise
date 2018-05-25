package com.bigtiger.kromise


interface Deferred<D, F, P>: Kromise<D, F, P>{
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
    fun resolve(resolve: D): Deferred<D, F, P>

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
    fun reject(reject: F): Deferred<D, F, P>

    /**
     * This should be called when a task is still executing and progress had been made,
     * E.g., during a file download, notify the download progress.
     *
     *
     * <pre>
     * `
     * [Deferred] deferredObject = new [DeferredObject]();
     * [Promise] kromise = deferredObject.kromise();
     * kromise.progress(new [ProgressCallback]() {
     * public void onProgress(Object progress) {
     * // Failed :(
     * }
     * });
     *
     * // another thread using the same deferredObject
     * deferredObject.reject("100%");
     *
    ` *
    </pre> *
     *
     * @param progress the intermediate result for this `Deferred`
     *
     * @return the reference to this `Deferred` instance.
     */
    fun notify(progress: P): Deferred<D, F, P>

    /**
     * Return an [Promise] instance (i.e., an observer).  You can register callbacks in this observer.
     *
     * @return the reference to this `Deferred` instance as a `Promise`,
     */
    fun kromise(): Kromise<D, F, P>
}