package com.piriurna.data.remote.sources

import com.piriurna.data.remote.HandleApi
import com.piriurna.data.remote.QuoteApi
import com.piriurna.data.remote.dto.quotes.QuoteDto
import com.piriurna.data.remote.dto.quotes.QuoteListDto
import javax.inject.Inject

class QuoteApiSource @Inject constructor(
    private val quoteApi: QuoteApi
) {

    suspend fun getRandomQuote() : QuoteDto {
        return HandleApi.safeApiCall { quoteApi.getRandomQuote() }
    }


    suspend fun getRandomQuoteList(numOfQuotes : Int) : QuoteListDto {
        return HandleApi.safeApiCall { quoteApi.getRandomQuoteList(numOfQuotes) }
    }
}