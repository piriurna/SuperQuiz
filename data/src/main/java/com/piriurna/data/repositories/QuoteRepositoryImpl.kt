package com.piriurna.data.repositories

import com.piriurna.data.mappers.toQuote
import com.piriurna.data.remote.sources.QuoteApiSource
import com.piriurna.domain.models.quotes.Quote
import com.piriurna.domain.repositories.QuoteRepository
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val quoteApiSource: QuoteApiSource,
) : QuoteRepository {

    override suspend fun getRandomQuote(): Quote {
        return quoteApiSource.getRandomQuote().toQuote()
    }
}