package com.marat.retrofittest.data.utils.characterswrapper.implementation

import com.marat.retrofittest.data.utils.characterswrapper.state.CharacterResult

object CharacterResultWrapper {

    fun <T> wrapPageResult(request: () -> T): CharacterResult<T> {
        return CharacterResult.Success(request())
    }
}