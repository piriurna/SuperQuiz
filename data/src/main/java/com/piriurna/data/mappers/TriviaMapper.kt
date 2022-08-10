package com.piriurna.data.mappers

import com.piriurna.data.database.entities.CategoryEntity
import com.piriurna.data.database.entities.QuestionEntity
import com.piriurna.domain.models.DifficultyType
import com.piriurna.data.remote.dto.CategoryDto
import com.piriurna.data.remote.dto.QuizDto
import com.piriurna.domain.models.Category
import com.piriurna.domain.models.Question
import com.piriurna.domain.models.QuestionType

fun CategoryDto.toCategory() : List<Category> {
    return this.triviaCategories.map { categoryDto ->
        return@map Category(
            id = categoryDto.id,
            name = categoryDto.name
        )
    }
}

fun CategoryEntity.toCategory() : Category {
    return Category(
        id = this.id,
        name = this.name
    )
}


fun Category.toCategoryEntity() : CategoryEntity {
    return CategoryEntity(
        id = this.id,
        name = this.name
    )
}

fun List<Category>.toCategoryEntity() : List<CategoryEntity> {
    return this.map {
        return@map it.toCategoryEntity()
    }
}


fun QuizDto.toQuestions(categoryId: Int) : List<Question> {
    return this.questions.map { questionDto ->
        val allAnswers = questionDto.incorrectAnswers.toMutableList()
        allAnswers.add(questionDto.correctAnswer)
        allAnswers.shuffle()

        return@map Question(
            categoryId = categoryId,
            correctAnswer = questionDto.correctAnswer,
            difficulty = DifficultyType.convertFromString(questionDto.difficulty),
            allAnswers = allAnswers,
            description = questionDto.question,
            type = QuestionType.convertFromString(questionDto.type)
        )
    }
}


fun Question.toQuestionEntity() : QuestionEntity {
    return QuestionEntity(
        categoryId = this.categoryId,
        difficulty = this.difficulty,
        type = this.type,
        description = this.description
    )
}

fun List<Question>.toQuestionEntity() : List<QuestionEntity> {
    return this.map {
        return@map it.toQuestionEntity()
    }
}

