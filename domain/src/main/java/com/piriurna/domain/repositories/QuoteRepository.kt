package com.piriurna.domain.repositories

import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.models.quotes.Quote

interface QuoteRepository {

    suspend fun getRandomQuote() : ApiNetworkResponse<Quote>


    suspend fun getRandomQuoteList(numOfQuotes : Int) : ApiNetworkResponse<List<Quote>>
}