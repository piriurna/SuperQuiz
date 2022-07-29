package com.piriurna.domain.repositories

import com.piriurna.domain.models.Category

interface TriviaRepository {

    suspend fun getCategories() : List<Category>
}