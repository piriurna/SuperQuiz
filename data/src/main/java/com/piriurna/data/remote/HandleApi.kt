package com.piriurna.data.remote

import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object HandleApi {

    suspend fun <T> safeApiCall(callFunction: suspend () -> T): T {
        return try{

            callFunction.invoke()

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