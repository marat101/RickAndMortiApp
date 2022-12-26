package com.marat.retrofittest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import com.marat.retrofittest.data.model.Character
import com.marat.retrofittest.data.model.Result
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface CharacterRepository {
    suspend fun getData()
}