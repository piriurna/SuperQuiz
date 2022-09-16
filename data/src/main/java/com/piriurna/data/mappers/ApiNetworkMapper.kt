package com.piriurna.data.mappers

import com.piriurna.domain.ApiNetworkError
import java.lang.Exception

fun Exception.toApiNetworkError() : ApiNetworkError {
    return ApiNetworkError(
        message = this.message
    )
}