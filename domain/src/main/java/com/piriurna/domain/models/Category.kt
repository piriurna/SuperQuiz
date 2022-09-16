package com.piriurna.domain.models

data class Category(
    val id: Int,
    val name: String,
    val completionRate : Int = 0 //0-100 in percentage
) {
    companion object {
        val mockCategoryList = listOf(
            Category(
                id = 17,
                name = "Physics",
                completionRate = 0
            ),
            Category(
                id = 9,
                name = "General Knowledge",
                completionRate = 80,
            ),
            Category(
                id = 19,
                name = "Mathematics",
                completionRate = 100
            ),
            Category(
                id = 23,
                name = "History",
                completionRate = 40
            ),
            Category(
                id = 25,
                name = "Art",
                completionRate = 40
            )
        )
    }
}