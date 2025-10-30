package com.test.myapplication.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data_store")

    companion object {
        val KEY_PROFILE_NAME = stringPreferencesKey("key_profile_name")
        val KEY_PROFILE_EMAIL = stringPreferencesKey("key_profile_email")
        val KEY_PROFILE_IMAGE = stringPreferencesKey("key_profile_image")
    }

    suspend fun saveProfileName(value: String) {
        context.dataStore.edit { preferences ->
            preferences[KEY_PROFILE_NAME] = value
        }
    }

    val loadProfileName: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[KEY_PROFILE_NAME] ?: ""
        }

    suspend fun saveProfileEmail(value: String) {
        context.dataStore.edit { preferences ->
            preferences[KEY_PROFILE_EMAIL] = value
        }
    }

    val loadProfileEmail: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[KEY_PROFILE_EMAIL] ?: ""
        }

    suspend fun saveProfileImage(value: String) {
        context.dataStore.edit { preferences ->
            preferences[KEY_PROFILE_IMAGE] = value
        }
    }

    val loadProfileImage: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[KEY_PROFILE_IMAGE] ?: ""
        }
}