package com.marat.retrofittest.data.model

import com.marat.retrofittest.data.model.Info
import com.marat.retrofittest.data.model.Result

data class Character(
    val info: Info,
    val results: List<Result>
)