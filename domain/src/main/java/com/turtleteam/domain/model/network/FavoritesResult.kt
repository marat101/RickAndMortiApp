package com.turtleteam.domain.model.network

sealed class FavoritesResult<out T> {

    data class Success<out T>(val value: T) : FavoritesResult<T>()

    data class Error(val error: Throwable? = null) : FavoritesResult<Nothing>()

    object ConnectionError : FavoritesResult<Nothing>()

    object Loading : FavoritesResult<Nothing>()
}
