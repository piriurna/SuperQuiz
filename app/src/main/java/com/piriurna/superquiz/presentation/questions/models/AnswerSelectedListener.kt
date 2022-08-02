package com.piriurna.superquiz.presentation.questions.models

interface AnswerSelectedListener {


    fun onAnswerSelected(answer: String)


    fun getSelectedAnswer() : String


    fun isAnswerSelected(answer: String) : Boolean{
        return answer == getSelectedAnswer()
    }
}