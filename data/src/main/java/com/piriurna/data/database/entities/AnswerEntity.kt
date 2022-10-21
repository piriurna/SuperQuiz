package com.piriurna.data.database.entities

import androidx.room.*

@Entity(tableName = "answer",
    foreignKeys = [
        ForeignKey(
            entity = QuestionEntity::class,
            parentColumns = arrayOf("questionId"),
            childColumns = arrayOf("ownerQuestionId"),
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class AnswerEntity(
    @PrimaryKey(autoGenerate = true)
    val answerId: Int = 0,
    val text: String,
    val isCorrectAnswer : Boolean = false,
    val isEnabled : Boolean = true,
    @ColumnInfo(index = true)
    val ownerQuestionId: Int
)
