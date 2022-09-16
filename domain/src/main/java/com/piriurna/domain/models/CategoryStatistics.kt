package com.piriurna.domain.models

data class CategoryStatistics(
    val categoryId: Int = 0,
    val totalNumberOfQuestions: Int = 0,
    val correctAnswers : Int = 0,
    val incorrectAnswers: Int = 0
) {
    
    constructor(categoryId: Int, questions: List<Question>) : this(
        categoryId,
        totalNumberOfQuestions = questions.size,
        correctAnswers = questions.count { it.isQuestionAnsweredCorrectly() },
        incorrectAnswers = questions.count { it.isQuestionAnsweredIncorrectly() }
    )

    fun getNotAnsweredQuestions() = totalNumberOfQuestions - correctAnswers - incorrectAnswers
}