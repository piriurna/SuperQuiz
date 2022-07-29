package com.piriurna.data.remote.sources

import com.piriurna.data.remote.HandleApi.safeApiCall
import com.piriurna.data.remote.TriviaApi
import com.piriurna.data.remote.dto.CategoryDto
import com.piriurna.data.remote.dto.QuizDto

class TriviaApiSource(
    private val triviaApi: TriviaApi
) {

    suspend fun getCategories() : CategoryDto {
        return safeApiCall{ triviaApi.getCategories() }
    }

    suspend fun getQuiz(categoryId: Int) : QuizDto {
        return safeApiCall { triviaApi.getQuiz(amount = "10", categoryId = categoryId.toString()) }
    }
}