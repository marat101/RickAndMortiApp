package com.marat.retrofittest.ui.fragments.favoritesfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.domain.model.Result
import com.turtleteam.domain.model.network.FavoritesResult
import com.turtleteam.domain.usecase.network.GetFavoriteCharactersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel(private val getIds: GetFavoriteCharactersUseCase) : ViewModel() {

    private val _favorites = MutableLiveData<FavoritesResult<List<Result>>>(FavoritesResult.Loading)
    val favorites: LiveData<FavoritesResult<List<Result>>> = _favorites

    fun getList() = viewModelScope.launch(Dispatchers.IO) {
        _favorites.postValue(getIds.execute())
    }
}