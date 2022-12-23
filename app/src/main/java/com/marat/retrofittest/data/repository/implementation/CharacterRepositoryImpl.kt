package com.marat.retrofittest.data.repository.implementation

import com.marat.retrofittest.data.api.ApiService
import com.marat.retrofittest.data.model.Character
import com.marat.retrofittest.data.repository.CharacterRepository
import retrofit2.Response
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(private val api: ApiService):
    CharacterRepository {

    override suspend fun getData(): Response<Character> = api.getCharacterList()
}