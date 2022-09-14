package com.marat.retrofittest

import android.widget.ImageView
import androidx.annotation.Nullable

data class Character(val id: Int,
                     val name: String,
                     @Nullable val img: String)
