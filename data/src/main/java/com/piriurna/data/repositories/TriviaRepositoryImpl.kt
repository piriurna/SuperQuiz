package com.piriurna.data.repositories

import com.piriurna.data.database.daos.AnswerDao
import com.piriurna.data.database.daos.CategoryDao
import com.piriurna.data.database.daos.QuestionDao
import com.piriurna.data.mappers.*
import com.piriurna.data.remote.sources.TriviaApiSource
import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.models.Answer
import com.piriurna.domain.models.Category
import com.piriurna.domain.models.Question
import com.piriurna.domain.repositories.TriviaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.Exception
import javax.inject.Inject


class TriviaRepositoryImpl @Inject constructor(
    private val triviaApiSource: TriviaApiSource,
    private val categoryDao: CategoryDao,
    private val questionDao: QuestionDao,
    private val answerDao: AnswerDao
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


    override suspend fun getCategoryQuestions(categoryId: Int, numberOfQuestions: Int): ApiNetworkResponse<List<Question>> {
        return try {
            val result = triviaApiSource.getQuiz(categoryId, numberOfQuestions).toQuestions(categoryId)
            ApiNetworkResponse(
                data = result
            )
        } catch (e : Exception) {
            ApiNetworkResponse(
                error = e.toApiNetworkError()
            )
        }
    }

    override suspend fun getQuestionFromDb(questionId: Int): Question? {
        return questionDao.getQuestion(questionId.toLong())?.toQuestion()
    }


    override suspend fun insertCategoryQuestionsInDb(questions: List<Question>): List<Long> {
        return questionDao.insertQuestions(questions.toQuestionEntity())
    }


    override fun getCategoryQuestionsFromDb(categoryId: Int): Flow<List<Question>> {
        return questionDao.getQuestions(categoryId).map { it.toQuestion() }
    }

    override suspend fun deleteCategoryQuestions(categoryId: Int) {
        questionDao.deleteCategoryQuestions(categoryId)
    }


    override suspend fun deleteCategories() {
        categoryDao.deleteCategories()
    }

    override suspend fun getQuestionsFromIdList(ids: List<Long>): List<Question> {
        return questionDao.getQuestions(ids)!!.toQuestion()
    }


    override fun getDbCategories(): Flow<List<Category>> {
        return categoryDao.getCategories().map {  list->
            list.map { categorystats-> categorystats.toCategory() }
        }
    }

    override fun getDbCategory(categoryId: Int): Flow<Category> {
        return categoryDao.getCategory(categoryId = categoryId).map { it.toCategory() }
    }

    override suspend fun disableAnswer(answerId: Int) {
        answerDao.disableAnswer(answerId, false)
    }

    override suspend fun insertCategoriesInDb(categories: List<Category>) {
        categoryDao.insertCategories(categories.toCategoryEntity())
    }

    override suspend fun insertAnswersInDb(answers: List<Answer>, questionId: Int) {
        answerDao.insertAnswers(answers.toAnswerEntity(questionId))
    }

    override suspend fun updateQuestion(question: Question) : Int {
        question.chosenAnswer?.let {
            return questionDao.updateQuestion(question.id.toLong(), it.id)
        }

        return 0
    }

    override suspend fun getNumberOfCategories(): Int {
        return categoryDao.getNumberOfCategories()
    }

    override suspend fun getMissingCategories(values: List<Int>): List<Int> {
        val ll = categoryDao.getMissingCategories(values)
        return ll
    }
}