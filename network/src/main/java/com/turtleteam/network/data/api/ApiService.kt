package com.turtleteam.network.data.api

import com.turtleteam.domain.model.Character
import com.turtleteam.domain.model.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character/{ids}")
    suspend fun getCharactersByIds(
        @Path("ids") ids: String,
    ): List<Result>

    @GET("character/")
    suspend fun getCharacterList(
        @Query("page") page: Int,
        @Query("name") name: String? = null,
        @Query("gender") gender: String? = null,
        @Query("status") status: String? = null,
    ): Response<Character>
}