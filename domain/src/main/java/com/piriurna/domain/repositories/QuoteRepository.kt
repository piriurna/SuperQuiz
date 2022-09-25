package com.piriurna.domain.repositories

import com.piriurna.domain.models.quotes.Quote

interface QuoteRepository {

    suspend fun getRandomQuote() : Quote
}