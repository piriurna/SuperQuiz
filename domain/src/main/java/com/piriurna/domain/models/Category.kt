package com.piriurna.domain.models

data class Category(
    val id: Int,
    val name: String,
    val completionRate : Int = 0, //0-100 in percentage
    private val title : String = "",
    private val subTitle : String = "",
) {

    val titleDesc: String
        get() = if (subTitle == "") title else subTitle

    val subTitleDesc: String?
        get() = if (title == "") null else title

    companion object {
        val mockCategoryList = listOf(
            Category(
                id = 17,
                name = "Physics",
                completionRate = 0,
                title = "Physics",
                subTitle = "Astral"

            ),
            Category(
                id = 9,
                name = "General Knowledge",
                completionRate = 80,
                title = "General Knowledge",
                subTitle = "Cooking"
            ),
            Category(
                id = 19,
                name = "Mathematics",
                completionRate = 100,
                subTitle = "Mathematics"
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