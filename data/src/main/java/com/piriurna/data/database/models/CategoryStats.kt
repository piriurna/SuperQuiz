package com.piriurna.data.database.models

import androidx.room.ColumnInfo




data class CategoryStats(

    @ColumnInfo(name = "categoryId")
    var categoryId: Int = 0,

    @ColumnInfo(name = "completionRate")
    var completionRate: Int = 0,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "subTitle")
    val subTitle: String,

    @ColumnInfo(name = "title")
    val title: String
)

