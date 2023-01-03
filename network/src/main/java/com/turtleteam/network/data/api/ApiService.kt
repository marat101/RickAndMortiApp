package com.turtleteam.network.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import com.turtleteam.domain.model.Character

interface ApiService {

    @GET("character/")
    suspend fun getCharacterList(
        @Query("page") page: Int,
        @Query("name") name: String? = null,
        @Query("gender") gender: String? = null,
        @Query("status") status: String? = null
    ): Response<Character>
}