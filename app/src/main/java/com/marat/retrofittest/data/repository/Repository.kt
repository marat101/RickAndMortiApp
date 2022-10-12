package com.marat.retrofittest.data.repository

import com.marat.retrofittest.data.api.RetrofitInstance
import com.marat.retrofittest.data.model.Character
import retrofit2.Response

class Repository {
    suspend fun getData(): Character = RetrofitInstance.api.getCharacterList().body()!!
}