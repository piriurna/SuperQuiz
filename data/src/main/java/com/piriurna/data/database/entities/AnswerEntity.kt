package com.piriurna.data.database.entities

import androidx.room.*

@Entity(tableName = "answer",
    foreignKeys = [
        ForeignKey(
            entity = QuestionEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("questionId"),
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class AnswerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val text: String,
    val isCorrectAnswer : Boolean = false,
    @ColumnInfo(index = true)
    val questionId: Int
)
