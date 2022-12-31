package com.turtleteam.network.data.utils.characterswrapper.state

sealed class CharacterResult<out T>{

    data class Success<out T>(val value: T) : CharacterResult<T>()

    object Error : CharacterResult<Nothing>()

    object Loading : CharacterResult<Nothing>()
}
