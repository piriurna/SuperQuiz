package com.piriurna.data.repositories

import com.piriurna.data.mappers.toApiNetworkError
import com.piriurna.data.mappers.toCategory
import com.piriurna.data.mappers.toQuestions
import com.piriurna.data.remote.sources.TriviaApiSource
import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.models.Category
import com.piriurna.domain.models.Question
import com.piriurna.domain.repositories.TriviaRepository
import java.lang.Exception


class TriviaRepositoryImpl(
    private val triviaApiSource: TriviaApiSource
): TriviaRepository {

    override suspend fun getCategories(): ApiNetworkResponse<List<Category>> {
        return try {
            val result = triviaApiSource.getCategories().toCategory()
            ApiNetworkResponse(
                data = result
            )
        } catch (e : Exception) {
            ApiNetworkResponse(
                error = e.toApiNetworkError()
            )
        }
    }


    override suspend fun getCategoryQuestions(categoryId: Int): ApiNetworkResponse<List<Question>> {
        return try {
            val result = triviaApiSource.getQuiz(categoryId).toQuestions()
            ApiNetworkResponse(
                data = result
            )
        } catch (e : Exception) {
            ApiNetworkResponse(
                error = e.toApiNetworkError()
            )
        }
    }

}