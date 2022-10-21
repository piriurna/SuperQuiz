package com.piriurna.data.mappers

import com.piriurna.data.remote.SQException
import com.piriurna.domain.ApiNetworkError

fun SQException.toApiNetworkError() : ApiNetworkError {
  return ApiNetworkError(
      code = this.code,
      message = this.message,
      errors = listOf(this.toString())
  )

}