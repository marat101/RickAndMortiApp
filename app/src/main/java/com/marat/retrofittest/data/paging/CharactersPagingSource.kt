package com.marat.retrofittest.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.marat.retrofittest.data.api.ApiService
import com.marat.retrofittest.data.model.Result
import retrofit2.HttpException

class CharactersPagingSource(
    private val api: ApiService,
) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        val anchorPos = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPos) ?: return null
        return page.prevKey?.plus(1)?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {

        val page = params.key ?: 1
        val pageSize = params.loadSize

        val response = api.getCharacterList(page)

        return if (response.isSuccessful){
            Log.e("response code", response.code().toString())
            val result = checkNotNull(response.body())
            val nextkey = if (result.results.size < pageSize) null else page + 1
            val prevKey = if (page==1) null else page - 1
            LoadResult.Page(result.results, prevKey, nextkey)
        }else{
            LoadResult.Error(HttpException(response))
        }
    }
}