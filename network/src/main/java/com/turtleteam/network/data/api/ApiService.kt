package com.turtleteam.network.data.api

import com.turtleteam.domain.model.Character
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("character/")
    suspend fun getCharacterList(
        @Query("page") page: Int
    ): Character
}