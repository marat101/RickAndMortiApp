package com.marat.retrofittest.data.api

import retrofit2.http.GET

interface ApiService {

    @GET("getData")
    fun getCharacterList(): List<Character>

}