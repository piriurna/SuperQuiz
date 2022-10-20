package com.piriurna.data.repositories

import com.piriurna.data.mappers.toApiNetworkError
import com.piriurna.data.mappers.toQuote
import com.piriurna.data.remote.SQException
import com.piriurna.data.remote.sources.QuoteApiSource
import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.models.quotes.Quote
import com.piriurna.domain.repositories.QuoteRepository
import java.lang.Exception
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val quoteApiSource: QuoteApiSource,
) : QuoteRepository {

    override suspend fun getRandomQuote(): ApiNetworkResponse<Quote> {
        return try {
            val result = quoteApiSource.getRandomQuote().toQuote()
            ApiNetworkResponse(
                data = result
            )
        } catch (e : SQException) {
            ApiNetworkResponse(
                error = e.toApiNetworkError()
            )
        }
    }


    override suspend fun getRandomQuoteList(numOfQuotes: Int): ApiNetworkResponse<List<Quote>> {
        return try {
            val result = quoteApiSource.getRandomQuoteList(numOfQuotes).toQuote()
            ApiNetworkResponse(
                data = result
            )
        } catch (e : SQException) {
            ApiNetworkResponse(
                error = e.toApiNetworkError()
            )
        }
    }
}