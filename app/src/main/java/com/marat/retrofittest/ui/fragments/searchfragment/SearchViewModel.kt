package com.marat.retrofittest.ui.fragments.searchfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.turtleteam.domain.usecase.SearchCharactersUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class SearchViewModel(private val searchUseCase: SearchCharactersUseCase): ViewModel() {

    var name: String = ""

    fun getData() = searchUseCase.execute(name).cachedIn(viewModelScope).stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
}