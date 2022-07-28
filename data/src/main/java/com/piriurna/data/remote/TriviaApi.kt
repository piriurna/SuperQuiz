package com.piriurna.data.remote

import com.piriurna.data.remote.dto.CategoryDto
import retrofit2.http.GET

interface TriviaApi {

    @GET("api_category.php")
    suspend fun getCategories(): CategoryDto


    //TODO: Get quiz com query params da categoria e do page size
}