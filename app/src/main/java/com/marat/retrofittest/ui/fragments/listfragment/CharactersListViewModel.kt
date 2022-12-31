package com.marat.retrofittest.ui.fragments.listfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.marat.retrofittest.data.model.Result
import com.marat.retrofittest.aaaaaaaaaaaaaaa.usecase.GetCharactersPageUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class CharactersListViewModel(private val getCharactersPage: GetCharactersPageUseCase) : ViewModel() {

    val characterList: StateFlow<PagingData<Result>> = getData().stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    private fun getData() = getCharactersPage.execute().cachedIn(viewModelScope)
}