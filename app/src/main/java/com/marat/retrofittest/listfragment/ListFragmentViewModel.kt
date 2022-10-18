package com.marat.retrofittest.listfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marat.retrofittest.data.model.Character
import com.marat.retrofittest.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListFragmentViewModel @Inject constructor(private val rep: CharacterRepository) : ViewModel() {
    private val _characterList: MutableLiveData<Character> = MutableLiveData()
    val characterList: LiveData<Character> = _characterList

    init {
        viewModelScope.launch {
            _characterList.value = rep.getData()
        }
    }
}