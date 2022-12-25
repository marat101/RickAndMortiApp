package com.marat.retrofittest.ui.fragments.listfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marat.retrofittest.data.model.Character
import com.marat.retrofittest.data.repository.CharacterRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class CharactersListViewModel (private val rep: CharacterRepository) : ViewModel() {
    private val _characterList = MutableLiveData<Response<Character>>()
    val characterList: LiveData<Response<Character>> = _characterList

    init {
        viewModelScope.launch {
            _characterList.value = rep.getData()
        }
    }
}