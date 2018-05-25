package com.bigtiger.kromise

interface CallbackExceptionHandler {
    fun handleException(callbackType: CallbackType, e: Exception)
}
 enum class CallbackType {
    DONE_CALLBACK,
    FAIL_CALLBACK,
    PROGRESS_CALLBACK,
    ALWAYS_CALLBACK
}