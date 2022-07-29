package com.piriurna.data.repositories

import com.piriurna.domain.models.Category
import com.piriurna.domain.repositories.TriviaRepository


class TriviaRepositoryImpl: TriviaRepository {

    override suspend fun getCategories(): List<Category> {
        return emptyList()
    }


}