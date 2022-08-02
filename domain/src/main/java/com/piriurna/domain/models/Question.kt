package com.piriurna.domain.models

data class Question(
    val category: String,
    val correctAnswer : String,
    val difficulty : String,
    val incorrectAnswers: List<String>,
    val question : String,
    val type : String
) {


    companion object {
        val mockQuestions = listOf(
            Question(
                category = "Science",
                type = "multiple",
                difficulty = "medium",
                question = "Two angles are complementary, if the sum of their measures is",
                correctAnswer = "-90",
                incorrectAnswers = listOf("-10", "-180", "-360")
            ),
            Question(
                category = "Sports",
                type = "multiple",
                difficulty = "medium",
                question = "With which team did Michael Schumacher make his Formula One debut at the 1991 Belgian Grand Prix?",
                correctAnswer = "Jordan",
                incorrectAnswers = listOf("Benetton","Ferrari","Mercedes")
            ),
            Question(
                category = "Entertainment: Video Games",
                type = "multiple",
                difficulty = "easy",
                question = "In what year was the first \"Mass Effect\" game released?",
                correctAnswer = "2007",
                incorrectAnswers = listOf("2008", "2009", "2010")
            )
        )
    }
}