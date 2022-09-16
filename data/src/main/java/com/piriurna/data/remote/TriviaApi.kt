package com.piriurna.data.remote

import com.piriurna.data.remote.dto.CategoryDto
import com.piriurna.data.remote.dto.QuizDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TriviaApi {

    @GET("api_category.php")
    suspend fun getCategories(): CategoryDto


    @GET("api.php")
    suspend fun getQuiz(
        @Query("amount") amount: String,
        @Query("category") categoryId : String
    ) : QuizDto


}