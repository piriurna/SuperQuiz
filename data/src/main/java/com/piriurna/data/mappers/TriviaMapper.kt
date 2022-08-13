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



//TODO: REFACTOR
fun QuizDto.toQuestions(categoryId: Int) : List<Question> {
    return this.questions.map { questionDto ->
        val allAnswers = questionDto.incorrectAnswers.map { Answer(id = 0, description = it, isCorrectAnswer = false) }.toMutableList()
        allAnswers.add(Answer(id = 0, description = questionDto.correctAnswer, isCorrectAnswer = true))
        allAnswers.shuffle()

        return@map Question(
            id = 0,
            categoryId = categoryId,
            correctAnswer = allAnswers.first{ it.isCorrectAnswer },
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

fun QuestionWithAnswers.toQuestion() : Question {
    val correctAnswer : AnswerEntity = this.answers.firstOrNull { it.isCorrectAnswer }?:AnswerEntity(id = 0, isCorrectAnswer = true, text = "", questionId = this.question.id)

    return Question(
        id = this.question.id,
        categoryId = this.question.categoryId,
        difficulty = this.question.difficulty,
        type = this.question.type,
        description = this.question.description,
        correctAnswer = correctAnswer.toAnswer(),
        allAnswers = this.answers.toAnswer()
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
        questionId = questionId
    )
}

fun List<Answer>.toAnswerEntity(questionId: Int) : List<AnswerEntity> {
    return this.map {
        return@map it.toAnswerEntity(questionId)
    }
}

fun AnswerEntity.toAnswer() : Answer {
    return Answer(
        id = this.id,
        description = this.text,
        isCorrectAnswer = this.isCorrectAnswer
    )
}

fun List<AnswerEntity>.toAnswer() : List<Answer> {
    return this.map {
        return@map it.toAnswer()
    }
}



