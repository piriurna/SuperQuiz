package com.piriurna.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.piriurna.domain.models.DifficultyType
import com.piriurna.domain.models.QuestionType

@Entity(tableName = "question",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = arrayOf("categoryId"),
            childColumns = arrayOf("ownerCategoryId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AnswerEntity::class,
            parentColumns = arrayOf("answerId"),
            childColumns = arrayOf("chosenAnswerId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class QuestionEntity(
    @ColumnInfo(index = true)
    val ownerCategoryId: Int,
    @PrimaryKey(autoGenerate = true)
    val questionId: Int = 0,
    val index : Int,
    val difficulty: DifficultyType,
    val type: QuestionType,
    val description: String,
    @ColumnInfo(index = true)
    val chosenAnswerId: Int? = null
)
