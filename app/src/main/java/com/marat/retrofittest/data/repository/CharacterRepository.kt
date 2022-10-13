package com.marat.retrofittest.data.repository

import com.marat.retrofittest.data.api.ApiService
import com.marat.retrofittest.data.model.Character
import javax.inject.Inject

class CharacterRepository @Inject constructor(private val api: ApiService) {
    suspend fun getData(): Character = api.getCharacterList().body()!!
}