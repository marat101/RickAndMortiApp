package com.turtleteam.network.data.utils.characterswrapper.implementation

import com.turtleteam.network.data.utils.characterswrapper.state.CharacterResult

object CharacterResultWrapper {

    fun <T> wrapPageResult(request: () -> T): CharacterResult<T> {
        return CharacterResult.Success(request())
    }
}