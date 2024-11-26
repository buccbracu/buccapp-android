package com.buccbracu.bucc.backend.local.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesDataStore(context: Context) {

    // DataStore instance
    private val Context.dataStore by preferencesDataStore(name = "preferences")
    private val dataStore = context.dataStore

    private val DARK_MODE = booleanPreferencesKey("dark_mode")

    suspend fun setDarkMode(enabled: Boolean){
        dataStore.edit { preferences ->
            preferences[DARK_MODE] = enabled
        }
    }
    val darkModeEnabled: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[DARK_MODE]?: false

    }

}
