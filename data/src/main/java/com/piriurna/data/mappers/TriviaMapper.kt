package com.piriurna.data.mappers

import android.text.Html
import com.piriurna.data.database.entities.AnswerEntity
import com.piriurna.data.database.entities.CategoryEntity
import com.piriurna.data.database.entities.QuestionEntity
import com.piriurna.data.database.models.CategoryStats
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

fun CategoryStats.toCategory() : Category {

    return Category(
        id = this.categoryEntity.categoryId,
        name = this.categoryEntity.name,
        title = this.title,
        description = this.subTitle,
        completionRate = this.completionRate,
        totalNumberOfQuestions = this.numberOfQuestions,
        correctAnswers = this.numberOfCorrectAnswers,
        incorrectAnswers = this.numberOfWrongAnswers,
        notAnsweredQuestions = this.numberOfNotAnsweredQuestions
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


fun QuizDto.toQuestions(categoryId: Int) : List<Question> {
    return this.questions.mapIndexed { index, questionDto ->
        val allAnswers = questionDto.incorrectAnswers.map { Answer(id = 0, description = it, isCorrectAnswer = false) }.toMutableList()
        allAnswers.add(Answer(id = 0, description = questionDto.correctAnswer, isCorrectAnswer = true))
        allAnswers.shuffle()

        return@mapIndexed Question(
            id = 0,
            index = index,
            categoryId = categoryId,
            difficulty = DifficultyType.convertFromString(questionDto.difficulty),
            allAnswers = allAnswers,
            description = Html.fromHtml(questionDto.question, Html.FROM_HTML_MODE_COMPACT).toString(),
            type = QuestionType.convertFromString(questionDto.type)
        )
    }
}




fun Question.toQuestionEntity() : QuestionEntity {
    return QuestionEntity(
        ownerCategoryId = this.categoryId,
        index = this.index,
        difficulty = this.difficulty,
        type = this.type,
        description = this.description,
        chosenAnswerId = this.chosenAnswer?.id
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
        index = this.question.index,
        categoryId = this.question.ownerCategoryId,
        difficulty = this.question.difficulty,
        type = this.question.type,
        description = Html.fromHtml(this.question.description, Html.FROM_HTML_MODE_COMPACT).toString(),
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
        ownerQuestionId = questionId,
        isEnabled = this.isEnabled
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
        description = Html.fromHtml(this.text, Html.FROM_HTML_MODE_COMPACT).toString(),
        isCorrectAnswer = this.isCorrectAnswer,
        isEnabled = this.isEnabled
    )
}

fun List<AnswerEntity>.toAnswer() : List<Answer> {
    return this.map {
        return@map it.toAnswer()
    }
}



