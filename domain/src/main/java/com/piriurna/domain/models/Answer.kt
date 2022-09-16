package com.piriurna.domain.models

data class Answer(
    val id : Int,
    val description : String,
    val isCorrectAnswer : Boolean,
) {

    companion object {
        fun getFirstQuestionMockAnswers() = listOf<Answer>(
            Answer(
                id = 0,
                description = "-90",
                isCorrectAnswer = true
            ),
            Answer(
                id = 1,
                description = "-10",
                isCorrectAnswer = false
            ),
            Answer(
                id = 2,
                description = "-180",
                isCorrectAnswer = false
            ),
            Answer(
                id = 3,
                description = "-360",
                isCorrectAnswer = false
            ),
        )

        fun getSecondQuestionMockAnswers() = listOf<Answer>(
            Answer(
                id = 4,
                description = "Jordan",
                isCorrectAnswer = true
            ),
            Answer(
                id = 5,
                description = "Benetton",
                isCorrectAnswer = false
            ),
            Answer(
                id = 6,
                description = "Ferrari",
                isCorrectAnswer = false
            ),
            Answer(
                id = 7,
                description = "Mercedes",
                isCorrectAnswer = false
            ),
        )

        fun getThirdQuestionMockAnswers() = listOf<Answer>(
            Answer(
                id = 8,
                description = "2007",
                isCorrectAnswer = true
            ),
            Answer(
                id = 9,
                description = "2008",
                isCorrectAnswer = false
            ),
            Answer(
                id = 10,
                description = "2009",
                isCorrectAnswer = false
            ),
            Answer(
                id = 11,
                description = "2010",
                isCorrectAnswer = false
            ),
        )
    }
}