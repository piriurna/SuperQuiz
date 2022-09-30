package com.piriurna.domain.models

data class Category(
    val id: Int,
    val name: String,
    val completionRate : Int = 0, //0-100 in percentage
    val title : String = "",
    private val description : String = "",
    val totalNumberOfQuestions : Int = 0,
    val correctAnswers : Int = 0,
    val incorrectAnswers : Int = 0,
) {

    val subTitle: String?
        get() = if (description == "") null else description

    companion object {
        val mockCategoryList = listOf(
            Category(
                id = 17,
                name = "Physics",
                completionRate = 0,
                title = "Physics",
                description = "Astral"

            ),
            Category(
                id = 9,
                name = "General Knowledge",
                completionRate = 80,
                title = "General Knowledge",
                description = "Cooking"
            ),
            Category(
                id = 19,
                name = "Mathematics",
                completionRate = 100,
                description = "Mathematics"
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