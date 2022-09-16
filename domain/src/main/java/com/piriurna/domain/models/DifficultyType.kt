package com.piriurna.domain.models

enum class DifficultyType(
    val description : String
) {
    EASY("easy"), MEDIUM("medium"), HARD("hard");

    companion object {
        fun convertFromString(difficulty : String) : DifficultyType {
            return values().firstOrNull { it.description == difficulty }?: EASY
        }
    }
}