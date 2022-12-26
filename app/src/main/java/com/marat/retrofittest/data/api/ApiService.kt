package com.marat.retrofittest.data.api

import com.marat.retrofittest.data.model.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("character/")
    suspend fun getCharacterList(
        @Query("page") page: Int
    ): Response<Character>
}