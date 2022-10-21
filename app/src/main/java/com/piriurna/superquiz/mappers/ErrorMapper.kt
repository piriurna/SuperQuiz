package com.piriurna.superquiz.mappers

import com.piriurna.common.models.SQError
import com.piriurna.data.remote.ErrorType


fun ErrorType?.toSQError(onRetry : () -> Unit) : SQError {
    return when(this) {
        ErrorType.INVALID_PARAMETER,
        ErrorType.INVALID_CATEGORY -> {
            SQError.Unavailable
        }

        ErrorType.NO_INTERNET -> {
            SQError.NoInternetConnection(onRetry)
        }

        else -> {
            SQError.GenericError(onRetry)
        }
    }
}