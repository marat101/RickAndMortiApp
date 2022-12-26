package com.marat.retrofittest.ui.fragments.listfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.marat.retrofittest.data.model.Result
import com.marat.retrofittest.data.paging.CharactersPagingSource
import kotlinx.coroutines.flow.Flow

class CharactersListViewModel(private val pagingSource: CharactersPagingSource) : ViewModel() {

    val characterList: Flow<PagingData<Result>> =
        Pager(PagingConfig(pageSize = 1, prefetchDistance = 20),
            pagingSourceFactory = { pagingSource }).flow.cachedIn(viewModelScope)
}