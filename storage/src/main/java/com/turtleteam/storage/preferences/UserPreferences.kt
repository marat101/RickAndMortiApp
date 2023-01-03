package com.turtleteam.storage.preferences

import android.content.Context

class UserPreferences(context: Context) {

    companion object{
        private const val SEARCH_PREFERENCES = "search"
    }

    private val preferences = context.getSharedPreferences(SEARCH_PREFERENCES, Context.MODE_PRIVATE)

}