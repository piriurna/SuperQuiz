package com.piriurna.domain.repositories

import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.models.Category

interface TriviaRepository {

    suspend fun getCategories() : ApiNetworkResponse<List<Category>>
}