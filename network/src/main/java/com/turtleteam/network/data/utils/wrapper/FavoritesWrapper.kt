package com.turtleteam.network.data.utils.wrapper

import com.turtleteam.domain.model.Result
import com.turtleteam.domain.model.network.FavoritesResult
import java.net.UnknownHostException

object FavoritesWrapper {

    suspend fun wrapInResult(request: suspend () -> List<Result>): FavoritesResult<List<Result>> {
        return try {
            val value = request()
            if (value.isEmpty() || value.none()) {
                FavoritesResult.Success(emptyList())
            } else {
                FavoritesResult.Success(value)
            }
        } catch (e: UnknownHostException){
            FavoritesResult.ConnectionError
        } catch (e: Throwable){
            FavoritesResult.Error(e)
        }
    }
}