package com.turtleteam.domain.usecase.network

import com.turtleteam.domain.repository.NetworkRepository

class GetCharactersPageUseCase(private val repository: NetworkRepository) {

    fun execute() = repository.fetchCharactersList()
}