package com.piriurna.data.remote

import com.piriurna.data.remote.dto.QuizDto
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object HandleApi {

    suspend fun <T> safeApiCall(callFunction: suspend () -> T): T {
        return try{

            val apiResponse: T = callFunction.invoke()


            if(apiResponse is QuizDto) {

                when(apiResponse.responseCode) {
                    ErrorType.INVALID_CATEGORY.code -> throw SQException.CategoryNotFoundException(
                        message = "Category not Found"
                    )

                    ErrorType.INVALID_PARAMETER.code -> throw SQException.InvalidParameterException(message = "Invalid parameters passed for the api call")
                }
            }

            apiResponse
        }
        catch (ex: Exception){
            when(ex){
                is HttpException -> {
                    throw SQException.NetworkException(code = ex.code(), message = ex.message())
                }
                is UnknownHostException -> throw SQException.NoInternetException

                is IOException -> throw SQException.NetworkException(code = ErrorType.IO.code, message = ex.message)

                is SocketTimeoutException -> throw SQException.TimeoutException(ex.message)

                else -> throw ex

            }
        }
    }



}