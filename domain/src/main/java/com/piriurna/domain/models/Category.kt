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
    val notAnsweredQuestions : Int = 0,
) {


    fun isSuccess() = (correctAnswers * 100f)/totalNumberOfQuestions > 55

    fun getPercentageOfCorrectAnswers() = try { correctAnswers * 100 / totalNumberOfQuestions } catch (e : ArithmeticException) { 0 }

    fun getPercentageOfCorrectAnsweredAnswers() = try { correctAnswers * 100 / getAnsweredQuestions() } catch (e : ArithmeticException) { 0 }

    fun getPercentageOfIncorrectAnswers() = try { incorrectAnswers * 100 / totalNumberOfQuestions } catch (e : ArithmeticException) { 0 }

    fun getAnsweredQuestions() = totalNumberOfQuestions - notAnsweredQuestions


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
                description = "Cooking",
                totalNumberOfQuestions = 10,
                correctAnswers = 7,
                incorrectAnswers = 2
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