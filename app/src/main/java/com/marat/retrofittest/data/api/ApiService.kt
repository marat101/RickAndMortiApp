package com.marat.retrofittest.data.api

import com.marat.retrofittest.data.model.Character
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("character")
    suspend fun getCharacterList(): Response<Character>
}