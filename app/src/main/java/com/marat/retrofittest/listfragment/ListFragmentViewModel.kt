package com.marat.retrofittest.listfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marat.retrofittest.data.repository.Repository
import com.marat.retrofittest.data.model.Character
import kotlinx.coroutines.launch

class ListFragmentViewModel : ViewModel() {
    private val rep = Repository()
    private val _characterList: MutableLiveData<Character> = MutableLiveData()
    val characterList: LiveData<Character> = _characterList

    init {
        viewModelScope.launch {
            _characterList.value = rep.getData()
        }
    }
}