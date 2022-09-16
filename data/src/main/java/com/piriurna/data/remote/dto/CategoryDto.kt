package com.piriurna.data.remote.dto


import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("trivia_categories")
    val triviaCategories: List<TriviaCategoryDto>
)