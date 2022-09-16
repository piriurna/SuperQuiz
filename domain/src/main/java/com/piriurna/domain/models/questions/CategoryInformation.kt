package com.piriurna.domain.models.questions

import com.piriurna.domain.models.Question

data class CategoryInformation(
    val questions: List<Question> = emptyList(),
    val numberOfQuestions: Int = 0,
)
