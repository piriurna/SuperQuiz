package com.piriurna.data.remote.dto


import com.google.gson.annotations.SerializedName

data class QuizDto(
    @SerializedName("response_code")
    val responseCode: Int,
    @SerializedName("results")
    val questions: List<QuestionDto>
)