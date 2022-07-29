package com.piriurna.data.remote.sources

import com.piriurna.data.remote.HandleApi.safeApiCall
import com.piriurna.data.remote.TriviaApi
import com.piriurna.data.remote.dto.CategoryDto

class TriviaApiSource(
    private val triviaApi: TriviaApi
) {

    suspend fun getCategories() : CategoryDto {
        return safeApiCall{ triviaApi.getCategories() }
    }
}