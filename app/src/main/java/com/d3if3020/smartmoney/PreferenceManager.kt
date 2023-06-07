package com.d3if3020.smartmoney

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "my_preferences")

class PreferencesManager(context: Context) {
    private val dataStore = context.dataStore

    private object Keys {
        val USERNAME = stringPreferencesKey("username")
        val IS_DARK_MODE = booleanPreferencesKey("is_dark_mode")
        // Tambahkan kunci preferensi lain sesuai kebutuhan
    }

    // Setter dan Getter untuk preferensi username
    suspend fun setUsername(username: String) {
        dataStore.edit { preferences ->
            preferences[Keys.USERNAME] = username
        }
    }

    val usernameFlow: Flow<String?> = dataStore.data.map { preferences ->
        preferences[Keys.USERNAME]
    }

    // Setter dan Getter untuk preferensi dark mode
    suspend fun setIsDarkMode(isDarkMode: Boolean) {
        dataStore.edit { preferences ->
            preferences[Keys.IS_DARK_MODE] = isDarkMode
        }
    }

    val isDarkModeFlow: Flow<Boolean?> = dataStore.data.map { preferences ->
        preferences[Keys.IS_DARK_MODE]
    }

    // Tambahkan setter dan getter untuk preferensi lainnya sesuai kebutuhan
}