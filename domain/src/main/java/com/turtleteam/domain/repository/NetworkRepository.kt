package com.turtleteam.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import com.turtleteam.domain.model.Result
import com.turtleteam.domain.model.network.FavoritesResult

interface NetworkRepository {

    suspend fun getCharactersByIds(ids:String): FavoritesResult<List<Result>>

    fun fetchCharactersList(
        name: String? = null,
        gender: String? = null,
        status: String? = null,
    ): Flow<PagingData<Result>>
}