package com.turtleteam.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun fetchCharactersList(
        name: String? = null,
        gender: String? = null,
        status: String? = null
    ): Flow<PagingData<com.turtleteam.domain.model.Result>>
}