package com.piriurna.domain.models

enum class QuestionType(
    val description: String
) {
    TRUE_FALSE("boolean"),
    MULTIPLE_CHOICE("multiple");


    companion object {
        fun convertFromString(description : String) : QuestionType {
            return values()
                .firstOrNull { it.description == description }?: MULTIPLE_CHOICE
        }
    }
}