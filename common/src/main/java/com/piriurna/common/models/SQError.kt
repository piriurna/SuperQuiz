package com.piriurna.common.models

import com.piriurna.common.R

sealed class SQError(
    val title : String,
    val subtitle : String,
    val imageResource : Int,
    val canRetry : Boolean,
    val retryText : String = "RETRY",
    val onRetry : () -> Unit = {}
) {

    class NoInternetConnection(onRetry: () -> Unit) : SQError(title = "Error!", subtitle = "No Internet Connection Available", imageResource =  R.drawable.ic_disconnected, canRetry = true, onRetry = onRetry)
    object Unavailable: SQError(title = "Error!", subtitle = "The service is currently unavailable.", imageResource = R.drawable.ic_unavailable, canRetry = false)
    class GenericError(onRetry : () -> Unit) : SQError(title = "Error!", subtitle = "An error has occurred", imageResource = R.drawable.ic_question, canRetry = true, onRetry = onRetry)
}