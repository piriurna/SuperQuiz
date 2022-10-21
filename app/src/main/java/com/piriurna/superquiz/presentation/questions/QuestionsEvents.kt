package com.piriurna.superquiz.presentation.questions

import com.piriurna.domain.models.Answer
import com.piriurna.domain.models.Question
import com.piriurna.superquiz.SQBaseEvent

sealed class QuestionsEvents : SQBaseEvent() {

    class GetCategory(val categoryId : Int) : QuestionsEvents()

    class GetQuestions(val categoryId : Int) : QuestionsEvents()

    class PerformHintAction(val question : Question) : QuestionsEvents()

    class FetchQuestionsForCategory(val categoryId : Int) : QuestionsEvents()

    object ShowResult : QuestionsEvents()

    object GoToNextPage : QuestionsEvents()

    class SelectAnswer(val answer: Answer) : QuestionsEvents()

    object DismissNoQuestionsPopup : QuestionsEvents()
}