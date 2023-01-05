package com.turtleteam.domain.usecase.network

import com.turtleteam.domain.model.Result
import com.turtleteam.domain.model.network.FavoritesResult
import com.turtleteam.domain.repository.NetworkRepository
import com.turtleteam.domain.repository.StorageRepository

class GetFavoriteCharactersUseCase(
    private val storage: StorageRepository,
    private val networkRepository: NetworkRepository
) {
    suspend fun execute(): FavoritesResult<List<Result>> {
        val ids = storage.setIds() ?: emptyList()
        if (ids.isEmpty()) return FavoritesResult.Success(emptyList())
        val path = StringBuilder("")
        ids.map { path.append("$it,") }
        return networkRepository.getCharactersByIds(path.toString())
    }
}