package com.marat.retrofittest.aaaaaaaaaaaaaaa.repository

import androidx.paging.PagingData
import com.marat.retrofittest.data.model.Result
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun fetchCharactersList(): Flow<PagingData<Result>>
}