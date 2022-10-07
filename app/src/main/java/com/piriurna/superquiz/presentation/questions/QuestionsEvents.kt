package com.piriurna.superquiz.presentation.questions

import com.piriurna.domain.models.Answer
import com.piriurna.domain.models.Question
import com.piriurna.superquiz.SQBaseEvent

sealed class QuestionsEvents : SQBaseEvent() {
    class GetQuestions(val categoryId : Int) : QuestionsEvents()

    class PerformHintAction(val question : Question) : QuestionsEvents()

    class SaveAnswer(val question: Question, val answer: Answer) : QuestionsEvents()

    class FetchQuestionsForCategory(val categoryId : Int) : QuestionsEvents()

    class GetNextQuestion() : QuestionsEvents()
}