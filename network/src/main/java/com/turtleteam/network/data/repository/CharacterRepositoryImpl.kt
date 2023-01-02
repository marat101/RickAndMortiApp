package com.turtleteam.network.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.turtleteam.domain.repository.CharacterRepository
import com.turtleteam.network.data.api.ApiService
import com.turtleteam.network.data.paging.CharactersPagingSource

class CharacterRepositoryImpl(private val apiService: ApiService) :
    CharacterRepository {

    override fun fetchCharactersList(
        name: String?,
        gender: String?,
        status: String?
    ) = Pager(PagingConfig(pageSize = 1, prefetchDistance = 20),
        pagingSourceFactory = { CharactersPagingSource(apiService, name, gender, status) }).flow
}