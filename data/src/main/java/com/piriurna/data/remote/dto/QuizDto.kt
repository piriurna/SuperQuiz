package com.piriurna.data.remote.dto


import com.google.gson.annotations.SerializedName

data class QuizDto(
    @SerializedName("response_code")
    val responseCode: Int,
    @SerializedName("results")
    val questions: List<QuestionDto>
) {

    companion object {
        const val SUCCESS = 0

        const val INVALID_CATEGORY_ID = 1

        const val INVALID_PARAMETER = 2
    }
}