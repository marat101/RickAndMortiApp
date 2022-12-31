package com.turtleteam.network.data.paging

import android.annotation.SuppressLint
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.turtleteam.domain.model.Result
import com.turtleteam.network.data.api.ApiService

class CharactersPagingSource(
    private val api: ApiService,
) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        val anchorPos = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPos) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    @SuppressLint("SuspiciousIndentation")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {

        val page = params.key ?: 1

        return try {
            val response = api.getCharacterList(page)
            val rresult = response.body()!!.results
            val result = mutableListOf<Result>()
            rresult.forEach { result.add(it) }
            Log.e("response code", response.toString())
            val nextkey = if (response.body()?.info!!.pages == page) null else page + 1
            val prevKey = if (page == 1) null else page - 1
            LoadResult.Page(result, prevKey, nextkey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}