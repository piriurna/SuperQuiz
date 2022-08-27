package com.piriurna.superquiz.presentation.questions

import com.piriurna.domain.models.Answer
import com.piriurna.superquiz.SQBaseEvent

sealed class QuestionsEvents : SQBaseEvent() {
    class GetQuestions(val categoryId : Int) : QuestionsEvents()

    class SaveAnswer(val questionId: Int, val answer: Answer) : QuestionsEvents()
}