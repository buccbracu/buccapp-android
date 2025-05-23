package com.buccbracu.bucc.backend.local.preferences

import android.content.Context


// Singleton instance of PreferencesDataStore
object Preferences {
    @Volatile
    private var instance: PreferencesDataStore? = null

    fun initialize(context: Context, systemDarkMode: Boolean) {
        synchronized(this) {
            if (instance == null) {
                instance = PreferencesDataStore(context, systemDarkMode)
            }
        }
    }

    fun get(): PreferencesDataStore {
        return instance ?: throw IllegalStateException("Preferences not initialized. Call initialize(context) first.")
    }
}
