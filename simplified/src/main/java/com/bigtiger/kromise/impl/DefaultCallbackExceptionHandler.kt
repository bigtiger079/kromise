package com.bigtiger.kromise.impl

import com.bigtiger.kromise.CallbackExceptionHandler
import com.bigtiger.kromise.CallbackType

class DefaultCallbackExceptionHandler: CallbackExceptionHandler {
    override fun handleException(callbackType: CallbackType, e: Exception) {
//        LOG.error("An uncaught exception occurred" +
//                " in " + callbackType, e)
    }
}