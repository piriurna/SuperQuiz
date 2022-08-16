package com.piriurna.data.mappers

import com.piriurna.data.database.entities.AnswerEntity
import com.piriurna.data.database.entities.CategoryEntity
import com.piriurna.data.database.entities.QuestionEntity
import com.piriurna.data.database.models.QuestionWithAnswers
import com.piriurna.data.remote.dto.CategoryDto
import com.piriurna.data.remote.dto.QuizDto
import com.piriurna.domain.models.*

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
        id = this.categoryId,
        name = this.name
    )
}


fun Category.toCategoryEntity() : CategoryEntity {
    return CategoryEntity(
        categoryId = this.id,
        name = this.name
    )
}

fun List<Category>.toCategoryEntity() : List<CategoryEntity> {
    return this.map {
        return@map it.toCategoryEntity()
    }
}



//TODO: REFACTOR
fun QuizDto.toQuestions(categoryId: Int) : List<Question> {
    return this.questions.map { questionDto ->
        val allAnswers = questionDto.incorrectAnswers.map { Answer(id = 0, description = it, isCorrectAnswer = false) }.toMutableList()
        allAnswers.add(Answer(id = 0, description = questionDto.correctAnswer, isCorrectAnswer = true))
        allAnswers.shuffle()

        return@map Question(
            id = 0,
            categoryId = categoryId,
            difficulty = DifficultyType.convertFromString(questionDto.difficulty),
            allAnswers = allAnswers,
            description = questionDto.question,
            type = QuestionType.convertFromString(questionDto.type)
        )
    }
}


fun Question.toQuestionEntity() : QuestionEntity {
    return QuestionEntity(
        ownerCategoryId = this.categoryId,
        difficulty = this.difficulty,
        type = this.type,
        description = this.description,
        chosenAnswerId = null
    )
}

fun List<Question>.toQuestionEntity() : List<QuestionEntity> {
    return this.map {
        return@map it.toQuestionEntity()
    }
}

fun QuestionWithAnswers.toQuestion() : Question {
    return Question(
        id = this.question.questionId,
        categoryId = this.question.ownerCategoryId,
        difficulty = this.question.difficulty,
        type = this.question.type,
        description = this.question.description,
        allAnswers = this.answers.toAnswer(),
        chosenAnswer = this.chosenAnswer?.toAnswer()
    )
}

fun List<QuestionWithAnswers>.toQuestion() : List<Question> {
    return this.map {
        return@map it.toQuestion()
    }
}

fun Answer.toAnswerEntity(questionId: Int) : AnswerEntity {
    return AnswerEntity(
        text = this.description,
        isCorrectAnswer = this.isCorrectAnswer,
        ownerQuestionId = questionId
    )
}

fun List<Answer>.toAnswerEntity(questionId: Int) : List<AnswerEntity> {
    return this.map {
        return@map it.toAnswerEntity(questionId)
    }
}

fun AnswerEntity.toAnswer() : Answer {
    return Answer(
        id = this.answerId,
        description = this.text,
        isCorrectAnswer = this.isCorrectAnswer
    )
}

fun List<AnswerEntity>.toAnswer() : List<Answer> {
    return this.map {
        return@map it.toAnswer()
    }
}



