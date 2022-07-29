package com.piriurna.domain

data class ApiNetworkResponse<T>(
    val data: T? = null,
    val error: ApiNetworkError = ApiNetworkError()
)

