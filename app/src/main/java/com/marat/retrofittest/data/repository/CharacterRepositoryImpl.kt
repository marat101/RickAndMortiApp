package com.marat.retrofittest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.marat.retrofittest.aaaaaaaaaaaaaaa.repository.CharacterRepository
import com.marat.retrofittest.data.paging.CharactersPagingSource

class CharacterRepositoryImpl(private val pagingSource: CharactersPagingSource) :
    CharacterRepository {

    override fun fetchCharactersList() = Pager(PagingConfig(pageSize = 1, prefetchDistance = 20),
        pagingSourceFactory = { pagingSource }).flow
}