package com.turtleteam.domain.usecase

import com.turtleteam.domain.repository.CharacterRepository

class SearchCharactersUseCase (private val repository: CharacterRepository) {

    fun execute(name: String?) = repository.fetchCharactersList(name = name)
}