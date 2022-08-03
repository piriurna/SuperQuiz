package com.piriurna.domain.repositories

import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.models.Category
import com.piriurna.domain.models.Question

interface TriviaRepository {

    suspend fun getCategories() : ApiNetworkResponse<List<Category>>

    suspend fun getCategoryQuestions(categoryId: Int) : ApiNetworkResponse<List<Question>>

    suspend fun getDbCategories() : List<Category>
}