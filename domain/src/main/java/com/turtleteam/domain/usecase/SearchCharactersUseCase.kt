package com.turtleteam.domain.usecase

import com.turtleteam.domain.repository.CharacterRepository

class SearchCharactersUseCase (private val repository: CharacterRepository) {

    fun execute(name: String?, gender: String?, status: String?) = repository.fetchCharactersList(name = name, gender = gender, status = status)
}