package com.piriurna.domain.models

data class CategoryStatistics(
    val categoryId: Int = 0,
    val totalNumberOfQuestions: Int = 0,
    val correctAnswers : Int = 0,
    val incorrectAnswers: Int = 0
) {
}