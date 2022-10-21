package com.piriurna.domain.usecases.quotes

import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.Resource
import com.piriurna.domain.models.quotes.Quote
import com.piriurna.domain.repositories.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRandomQuoteListUseCase @Inject constructor(
    private val quoteRepository: QuoteRepository
) {

    operator fun invoke(numOfQuotes : Int) : Flow<Resource<List<Quote>>> = flow {
        emit(Resource.Loading())

        val quoteResponse : ApiNetworkResponse<List<Quote>> = quoteRepository.getRandomQuoteList(numOfQuotes)

        quoteResponse.data?.let { quotes ->
            emit(Resource.Success(quotes))
        }?: run {
            emit(Resource.Error(ERROR_GETTING_A_QUOTE))
        }
    }


    companion object {
        const val ERROR_GETTING_A_QUOTE = "Error getting a quote"
    }

}