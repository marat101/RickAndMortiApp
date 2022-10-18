package com.marat.retrofittest.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.marat.retrofittest.data.api.ApiService
import com.marat.retrofittest.data.model.Character

class CharactersPagingSource(
    private val api: ApiService
    private val query: String
) : PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        TODO("Not yet implemented")
    }
}