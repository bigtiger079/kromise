package com.bigtiger.kromise.impl

import com.bigtiger.kromise.CallbackExceptionHandler



object GlobalConfiguration {
    private var globalCallbackExceptionHandler: CallbackExceptionHandler = DefaultCallbackExceptionHandler()

    fun setGlobalCallbackExceptionHandler(callbackExceptionHandler: CallbackExceptionHandler?) {
        if (callbackExceptionHandler == null) {
            throw IllegalArgumentException("callbackExceptionHandler cannot be null")
        }
        globalCallbackExceptionHandler = callbackExceptionHandler
    }

    fun getGlobalCallbackExceptionHandler(): CallbackExceptionHandler {
        return globalCallbackExceptionHandler
    }
}