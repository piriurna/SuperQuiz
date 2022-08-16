package com.piriurna.data.database.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.piriurna.data.database.entities.AnswerEntity
import com.piriurna.data.database.entities.QuestionEntity

data class QuestionWithAnswers(
    @Embedded val question: QuestionEntity,
    @Relation(
        parentColumn = "questionId",
        entityColumn = "ownerQuestionId"
    )
    val answers : List<AnswerEntity>,
    @Relation(
        parentColumn = "chosenAnswerId",
        entityColumn = "answerId"
    )
    val chosenAnswer: AnswerEntity?
)
