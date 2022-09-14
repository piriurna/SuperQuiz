package com.piriurna.domain.models


//TODO: Problema, nao temos id quando buscamos do servico, o id só é gerado quando inserimos na base de dados
data class Question(
    val id : Int,
    val index : Int,
    val categoryId: Int,
    val difficulty : DifficultyType,
    val allAnswers : List<Answer>,
    val description : String,
    val type : QuestionType,
    val chosenAnswer: Answer? = null
) {

    fun getCorrectAnswer() : Answer? {
        return allAnswers.firstOrNull { it.isCorrectAnswer }
    }

    fun isQuestionAnswered() = chosenAnswer != null

    fun isQuestionAnsweredCorrectly() = chosenAnswer == getCorrectAnswer()

    companion object {
        val mockQuestions = listOf(
            Question(
                id = 0,
                index = 0,
                categoryId = 9,
                type = QuestionType.MULTIPLE_CHOICE,
                difficulty = DifficultyType.MEDIUM,
                description = "Two angles are complementary, if the sum of their measures is",
                allAnswers = Answer.getFirstQuestionMockAnswers()
            ),
            Question(
                id = 1,
                index = 1,
                categoryId = 9,
                type = QuestionType.MULTIPLE_CHOICE,
                difficulty = DifficultyType.MEDIUM,
                description = "With which team did Michael Schumacher make his Formula One debut at the 1991 Belgian Grand Prix?",
                allAnswers = Answer.getSecondQuestionMockAnswers()
            ),
            Question(
                id = 2,
                index = 2,
                categoryId = 9,
                type = QuestionType.MULTIPLE_CHOICE,
                difficulty = DifficultyType.EASY,
                description = "In what year was the first \"Mass Effect\" game released?",
                allAnswers = Answer.getThirdQuestionMockAnswers()
            ),
            Question(
                id = 3,
                index = 3,
                categoryId = 10,
                type = QuestionType.MULTIPLE_CHOICE,
                difficulty = DifficultyType.MEDIUM,
                description = "With which team did Michael Schumacher make his Formula One debut at the 1991 Belgian Grand Prix?",
                allAnswers = Answer.getSecondQuestionMockAnswers()
            ),
            Question(
                id = 4,
                index = 4,
                categoryId = 11,
                type = QuestionType.MULTIPLE_CHOICE,
                difficulty = DifficultyType.EASY,
                description = "In what year was the first \"Mass Effect\" game released?",
                allAnswers = Answer.getThirdQuestionMockAnswers()
            )
        )
    }
}