package com.marat.retrofittest.listfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marat.retrofittest.data.repository.Repository
import com.marat.retrofittest.model.Character
import kotlinx.coroutines.launch
import retrofit2.Response

class ListFragmentViewModel : ViewModel() {
    val rep = Repository()
    val characterList: MutableLiveData<Response<Character>> = MutableLiveData()

    fun getCharacterList() {
        viewModelScope.launch {
            characterList.value = rep.getData()
        }
    }
}