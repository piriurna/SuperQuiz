package com.piriurna.data.mappers

import com.piriurna.data.remote.dto.CategoryDto
import com.piriurna.data.remote.dto.QuizDto
import com.piriurna.domain.models.Category
import com.piriurna.domain.models.Question

fun CategoryDto.toCategory() : List<Category> {
    return this.triviaCategories.map { categoryDto ->
        return@map Category(
            id = categoryDto.id,
            name = categoryDto.name
        )
    }
}


fun QuizDto.toQuestions() : List<Question> {
    return this.questions.map { questionDto ->
        return@map Question(
            category = questionDto.category,
            correctAnswer = questionDto.correctAnswer,
            difficulty = questionDto.difficulty,
            incorrectAnswers = questionDto.incorrectAnswers,
            question = questionDto.question,
            type = questionDto.type
        )
    }
}