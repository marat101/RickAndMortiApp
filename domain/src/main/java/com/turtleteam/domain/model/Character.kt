package com.turtleteam.domain.model

import com.turtleteam.domain.model.Info
import com.turtleteam.domain.model.Result

data class Character(
    val info: Info,
    val results: List<Result>
)