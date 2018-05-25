package com.bigtiger.kromise

interface AlwaysCallback<D, F> {
    /**
     * Invoked when the `Promise` resolves or rejects a value.
     *
     * @param state    the state of the `Promise`. Either [Promise.State.RESOLVED] or [Promise.State.REJECTED]
     * @param resolved the resolved value (if any) of the `Promise`
     * @param rejected the rejected value (if any) of the `Promise`
     */
    fun onAlways(state: State, resolved: D?, rejected: F?)
}