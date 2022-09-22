package com.marat.retrofittest.data.api

import com.marat.retrofittest.model.Character
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("api/character")
    suspend fun getCharacterList(): Response<Character>
}