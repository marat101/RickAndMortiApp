package com.marat.retrofittest.aaaaaaaaaaaaaaa.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun fetchCharactersList(): Flow<PagingData<com.turtleteam.domain.model.Result>>
}