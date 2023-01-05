package com.turtleteam.domain.repository

import androidx.paging.PagingData
import com.turtleteam.domain.model.Result
import com.turtleteam.domain.model.network.FavoritesResult
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {

    suspend fun getCharactersByIds(ids: String): FavoritesResult<List<Result>>

    fun fetchCharactersList(
        name: String? = null,
        gender: String? = null,
        status: String? = null,
    ): Flow<PagingData<Result>>
}