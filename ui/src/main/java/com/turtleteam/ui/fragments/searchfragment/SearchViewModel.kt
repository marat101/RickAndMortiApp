package com.turtleteam.ui.fragments.searchfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.turtleteam.domain.usecase.network.SearchCharactersUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class SearchViewModel(private val searchUseCase: SearchCharactersUseCase) : ViewModel() {

    var name: String = ""
    var gender: String = "select"
    var status: String = "select"

    fun getData() = searchUseCase.execute(
        name = name,
        gender = if (gender != "select") gender.lowercase() else null,
        status = if (status != "select") status.lowercase() else null,
    ).cachedIn(viewModelScope).stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
}