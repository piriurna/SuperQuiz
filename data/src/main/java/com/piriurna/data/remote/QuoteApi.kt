package com.piriurna.data.remote

import com.piriurna.data.remote.dto.CategoryDto
import com.piriurna.data.remote.dto.QuizDto
import com.piriurna.data.remote.dto.quotes.QuoteDto
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteApi {

    @GET("random")
    suspend fun getRandomQuote(): QuoteDto

}