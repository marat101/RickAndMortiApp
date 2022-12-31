package com.marat.retrofittest.aaaaaaaaaaaaaaa.usecase

import com.marat.retrofittest.aaaaaaaaaaaaaaa.repository.CharacterRepository

class GetCharactersPageUseCase(private val repository: CharacterRepository) {

    fun execute() = repository.fetchCharactersList()
}