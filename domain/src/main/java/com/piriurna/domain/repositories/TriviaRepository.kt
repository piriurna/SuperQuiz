package com.piriurna.domain.repositories

import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.models.Answer
import com.piriurna.domain.models.Category
import com.piriurna.domain.models.Question
import kotlinx.coroutines.flow.Flow

interface TriviaRepository {

    suspend fun getCategories() : ApiNetworkResponse<List<Category>>

    suspend fun getCategoryQuestions(categoryId: Int, numberOfQuestions: Int) : ApiNetworkResponse<List<Question>>

    suspend fun insertCategoryQuestionsInDb(questions: List<Question>) : List<Long>

    suspend fun deleteCategoryQuestions(categoryId: Int)

    fun getCategoryQuestionsFromDb(categoryId: Int): Flow<List<Question>>

    suspend fun getQuestionFromDb(questionId: Int) : Question?

    suspend fun getQuestionsFromIdList(ids: List<Long>) : List<Question>

    fun getDbCategories(): Flow<List<Category>>
    fun getDbCategory(categoryId: Int): Flow<Category>

    suspend fun disableAnswer(answerId: Int)

    suspend fun insertCategoriesInDb(categories: List<Category>)

    suspend fun insertAnswersInDb(answers: List<Answer>, questionId: Int)

    suspend fun updateQuestion(question: Question) : Int

    suspend fun getNumberOfCategories() : Int
    suspend fun getMissingCategories(values: List<Int>): List<Int>
}