package com.turtleteam.network.data.paging

import android.annotation.SuppressLint
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.turtleteam.domain.model.Result
import com.turtleteam.network.data.api.ApiService
import retrofit2.HttpException

class CharactersPagingSource(
    private val api: ApiService,
    private val name: String? = null,
    private val gender: String? = null,
    private val status: String? = null,
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
            val response = api.getCharacterList(page, name = name, gender = gender, status = status)

            if (response.code() == 404) throw HttpException(response)

            val result = response.body()!!.results
            Log.e("response code", response.toString())
            val nextkey = if (response.body()?.info!!.pages == page) null else page + 1
            val prevKey = if (page == 1) null else page - 1
            LoadResult.Page(result, prevKey, nextkey)
        } catch (e: HttpException) {
            if (e.code() == 404) LoadResult.Page(emptyList(), null, null) else LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}