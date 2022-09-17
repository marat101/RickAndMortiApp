package com.marat.retrofittest.model

import androidx.annotation.Nullable

data class Character(val id: Int,
                     val name: String,
                     @Nullable val img: String)
