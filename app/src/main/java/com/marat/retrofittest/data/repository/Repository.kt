package com.marat.retrofittest.data.repository

import com.marat.retrofittest.data.api.RetrofitInstance
import com.marat.retrofittest.model.Character
import retrofit2.Response

class Repository {
        suspend fun getData(): Response<Character> {
            return RetrofitInstance.api.getCharacterList()
    }
}