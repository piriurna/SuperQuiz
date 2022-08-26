package com.piriurna.superquiz.presentation.questions

import com.piriurna.superquiz.SQBaseEvent

sealed class QuestionsEvents : SQBaseEvent() {
    class GetQuestions(val categoryId : Int) : QuestionsEvents()
}