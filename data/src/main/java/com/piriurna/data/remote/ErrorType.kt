package com.piriurna.data.remote

enum class ErrorType(val code : Int) {

    INVALID_CATEGORY(1),
    INVALID_PARAMETER(2),
    NO_INTERNET(3),
    TIMEOUT(4),
    IO(5);


    companion object {
        fun valueFromCode(code: Int?) : ErrorType? {
            return values().firstOrNull { it.code == code }
        }
    }
}