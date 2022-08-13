package com.piriurna.domain.models


//TODO: Problema, nao temos id quando buscamos do servico, o id só é gerado quando inserimos na base de dados
data class Question(
    val id : Int,
    val categoryId: Int,
    val correctAnswer : Answer, //TODO: Com a entity da answer agora sabemos se é correta ou nao a partir da variavel do Answer, ja nao é mais preciso esse campo
    val difficulty : DifficultyType,
    val allAnswers : List<Answer>,
    val description : String,
    val type : QuestionType
) {

    companion object {
        val mockQuestions = listOf(
            Question(
                id = 0,
                categoryId = 9,
                type = QuestionType.MULTIPLE_CHOICE,
                difficulty = DifficultyType.MEDIUM,
                description = "Two angles are complementary, if the sum of their measures is",
                correctAnswer = Answer.getFirstQuestionMockAnswers()[0],
                allAnswers = Answer.getFirstQuestionMockAnswers()
            ),
            Question(
                id = 1,
                categoryId = 10,
                type = QuestionType.MULTIPLE_CHOICE,
                difficulty = DifficultyType.MEDIUM,
                description = "With which team did Michael Schumacher make his Formula One debut at the 1991 Belgian Grand Prix?",
                correctAnswer = Answer.getSecondQuestionMockAnswers()[0],
                allAnswers = Answer.getSecondQuestionMockAnswers()
            ),
            Question(
                id = 2,
                categoryId = 11,
                type = QuestionType.MULTIPLE_CHOICE,
                difficulty = DifficultyType.EASY,
                description = "In what year was the first \"Mass Effect\" game released?",
                correctAnswer = Answer.getThirdQuestionMockAnswers()[0],
                allAnswers = Answer.getThirdQuestionMockAnswers()
            )
        )
    }
}