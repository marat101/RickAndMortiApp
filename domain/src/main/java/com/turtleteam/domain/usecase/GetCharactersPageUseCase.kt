package com.marat.retrofittest.aaaaaaaaaaaaaaa.usecase

import com.turtleteam.domain.repository.CharacterRepository

class GetCharactersPageUseCase(private val repository: CharacterRepository) {

    fun execute() = repository.fetchCharactersList()
}