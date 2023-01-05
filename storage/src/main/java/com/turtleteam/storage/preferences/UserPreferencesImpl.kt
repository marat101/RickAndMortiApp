package com.turtleteam.storage.preferences

import android.content.Context
import com.turtleteam.domain.repository.StorageRepository

class UserPreferencesImpl(context: Context) : StorageRepository {

    companion object {
        private const val PREFS_NAME = "prefs"
        private const val SAVE_IDS = "saveids"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveIds(list: List<String>) {
        preferences.edit().putStringSet(SAVE_IDS, HashSet<String>(list)).apply()
    }

    override fun setIds(): List<String>? = preferences.getStringSet(SAVE_IDS, null)?.toList()
}