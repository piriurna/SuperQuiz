package com.piriurna.superquiz.presentation.splash.models

import com.piriurna.common.R
import com.piriurna.data.remote.ErrorType

enum class SplashError(
    val title : String,
    val subtitle : String,
    val imageResource : Int,
    val canRetry : Boolean = true
) {

    NO_INTERNET_CONNECTION("Error!", "No Internet Connection Available", R.drawable.ic_disconnected),
    UNAVAILABLE("Error!", "The service is currently unavailable.", R.drawable.ic_unavailable, false),
    GENERIC_ERROR("Error!", "An error has occurred", R.drawable.ic_question);


    companion object {

        fun getFromCode(errorCode : Int?) : SplashError{
            val errorType = ErrorType.valueFromCode(errorCode)

            return getFromCode(errorType)
        }


        fun getFromCode(errorType: ErrorType?) : SplashError {
            return when(errorType) {
                ErrorType.INVALID_PARAMETER,
                ErrorType.INVALID_CATEGORY -> {
                    UNAVAILABLE
                }

                ErrorType.NO_INTERNET -> {
                    NO_INTERNET_CONNECTION
                }

                else -> {
                    GENERIC_ERROR
                }
            }
        }
    }
}