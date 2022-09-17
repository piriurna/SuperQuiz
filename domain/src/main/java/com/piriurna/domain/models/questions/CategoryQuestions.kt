package com.piriurna.domain.models.questions

import com.piriurna.domain.models.Question

data class CategoryQuestions(
    val questions: List<Question> = emptyList(),
    val numberOfQuestions: Int = 0,
)
