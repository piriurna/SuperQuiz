package com.piriurna.data.remote

sealed class SQException(val code : Int?, message : String?) : Exception(message) {

    class CategoryNotFoundException(val categoryId : Int, message: String) : SQException(code = ErrorType.INVALID_CATEGORY.code, message = message)

    class InvalidParameterException(message: String) : SQException(code = ErrorType.INVALID_PARAMETER.code, message = message)

    class NetworkException(code : Int, message : String?) : SQException(code, message)

    object NoInternetException : SQException(code = ErrorType.NO_INTERNET.code, message = "No internet connection available")

    class TimeoutException(message : String?) : SQException(code = ErrorType.TIMEOUT.code, message = message)
}