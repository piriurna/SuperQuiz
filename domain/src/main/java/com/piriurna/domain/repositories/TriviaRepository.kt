package com.piriurna.domain.repositories

import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.models.Answer
import com.piriurna.domain.models.Category
import com.piriurna.domain.models.Question

interface TriviaRepository {

    suspend fun getCategories() : ApiNetworkResponse<List<Category>>

    suspend fun getCategoryQuestions(categoryId: Int) : ApiNetworkResponse<List<Question>>

    suspend fun insertCategoryQuestionsInDb(questions: List<Question>) : List<Long>

    suspend fun getCategoryQuestionsFromDb(categoryId: Int) : List<Question>

    suspend fun getQuestionFromDb(questionId: Int) : Question?

    suspend fun getQuestionsFromIdList(ids: List<Long>) : List<Question>

    suspend fun getDbCategories() : List<Category>

    suspend fun insertCategoriesInDb(categories: List<Category>)

    suspend fun insertAnswersInDb(answers: List<Answer>, questionId: Int)

    suspend fun updateQuestion(question: Question) : Int
}