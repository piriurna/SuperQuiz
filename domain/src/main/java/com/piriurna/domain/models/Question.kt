package com.piriurna.domain.models

data class Question(
    val categoryId: Int,
    val correctAnswer : String,
    val difficulty : DifficultyType,
    val allAnswers : List<String>,
    val description : String,
    val type : QuestionType
) {

    companion object {
        val mockQuestions = listOf(
            Question(
                categoryId = 9,
                type = QuestionType.MULTIPLE_CHOICE,
                difficulty = DifficultyType.MEDIUM,
                description = "Two angles are complementary, if the sum of their measures is",
                correctAnswer = "-90",

                allAnswers = listOf("-10", "-90", "-180", "-360")
            ),
            Question(
                categoryId = 10,
                type = QuestionType.MULTIPLE_CHOICE,
                difficulty = DifficultyType.MEDIUM,
                description = "With which team did Michael Schumacher make his Formula One debut at the 1991 Belgian Grand Prix?",
                correctAnswer = "Jordan",
                allAnswers = listOf("Benetton", "Jordan", "Ferrari", "Mercedes")
            ),
            Question(
                categoryId = 11,
                type = QuestionType.MULTIPLE_CHOICE,
                difficulty = DifficultyType.EASY,
                description = "In what year was the first \"Mass Effect\" game released?",
                correctAnswer = "2007",
                allAnswers = listOf("2007", "2008", "2009", "2010")
            )
        )
    }
}