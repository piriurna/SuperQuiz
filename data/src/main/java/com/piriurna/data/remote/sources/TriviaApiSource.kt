package com.piriurna.data.remote.sources

import androidx.annotation.IntRange
import com.piriurna.data.mappers.toQuestions
import com.piriurna.data.remote.HandleApi.safeApiCall
import com.piriurna.data.remote.TriviaApi
import com.piriurna.data.remote.dto.CategoryDto
import com.piriurna.data.remote.dto.QuizDto
import com.piriurna.domain.models.Question
import javax.inject.Inject

class TriviaApiSource @Inject constructor(
    private val triviaApi: TriviaApi
) {

    suspend fun getCategories() : CategoryDto {
        return safeApiCall{ triviaApi.getCategories() }
    }

    suspend fun getQuiz(categoryId: Int, numberOfQuestions : Int) : List<Question> {
        return safeApiCall { triviaApi.getQuiz(amount = numberOfQuestions.toString(), categoryId = categoryId.toString()).toQuestions(categoryId) }
    }
}