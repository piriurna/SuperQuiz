package com.piriurna.data.remote.sources

import com.piriurna.data.remote.HandleApi
import com.piriurna.data.remote.QuoteApi
import com.piriurna.data.remote.dto.quotes.QuoteDto
import javax.inject.Inject

class QuoteApiSource @Inject constructor(
    private val quoteApi: QuoteApi
) {

    suspend fun getRandomQuote() : QuoteDto {
        return HandleApi.safeApiCall { quoteApi.getRandomQuote() }
    }
}