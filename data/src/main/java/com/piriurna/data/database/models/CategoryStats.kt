package com.piriurna.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.piriurna.data.database.entities.CategoryEntity
import com.piriurna.data.database.entities.QuestionEntity


data class CategoryStats(

    @Embedded
    val categoryEntity: CategoryEntity,

    @ColumnInfo(name = "completionRate")
    var completionRate: Int = 0,

    @ColumnInfo(name = "numberOfQuestions")
    var numberOfQuestions: Int = 0,

    @ColumnInfo(name = "numberOfCorrectAnswers")
    var numberOfCorrectAnswers: Int = 0,

    @ColumnInfo(name = "numberOWrongAnswers")
    var numberOfWrongAnswers: Int = 0,

    @ColumnInfo(name = "subTitle")
    val subTitle: String,

    @ColumnInfo(name = "title")
    val title: String
)

