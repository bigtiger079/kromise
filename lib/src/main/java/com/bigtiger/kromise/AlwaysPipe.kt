package com.bigtiger.kromise



interface AlwaysPipe< D, F, D_OUT, F_OUT, P> {
    /**
     * Invoked when the `Promise` resolves or rejects a value.
     *
     * @param state    the state of the `Promise`. Either [State.RESOLVED] or [State.REJECTED]
     * @param resolved the resolved value (if any) of the `Promise`
     * @param rejected the rejected value (if any) of the `Promise`
     */
    fun pipeAlways(state: State, resolved: D, rejected: F): Kromise<D_OUT, F_OUT, P>
}