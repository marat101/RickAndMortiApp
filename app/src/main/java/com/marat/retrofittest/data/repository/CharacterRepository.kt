package com.marat.retrofittest.data.repository

import com.marat.retrofittest.data.model.Character
import retrofit2.Response

interface CharacterRepository {
    suspend fun getData(): Response<Character>
}