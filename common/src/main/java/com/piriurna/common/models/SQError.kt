package com.piriurna.common.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.piriurna.common.R

sealed class SQError(
    @StringRes val title : Int,
    @StringRes val subtitle : Int,
    @DrawableRes val imageResource : Int,
    val canRetry : Boolean,
    @StringRes val retryText : Int = R.string.retry,
    val onRetry : () -> Unit = {}
) {

    class NoInternetConnection(onRetry: () -> Unit) : SQError(title = R.string.error, subtitle = R.string.no_internet_connection_available, imageResource =  R.drawable.ic_disconnected, canRetry = true, onRetry = onRetry)
    object Unavailable: SQError(title = R.string.error, subtitle = R.string.the_service_is_currently_unavailable, imageResource = R.drawable.ic_unavailable, canRetry = false)
    class GenericError(onRetry : () -> Unit) : SQError(title = R.string.error, subtitle = contextR.string.an_error_has_occurred, imageResource = R.drawable.ic_question, canRetry = true, onRetry = onRetry)
}