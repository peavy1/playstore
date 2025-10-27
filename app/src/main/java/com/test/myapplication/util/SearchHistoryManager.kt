package com.test.myapplication.util

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val Context.dataStore by preferencesDataStore(name = "search_history_prefs")

class SearchHistoryManager(context: Context) {

    private val dataStore = context.dataStore

    companion object {
        private val HISTORY_KEY = stringPreferencesKey("search_history_list")
        private const val MAX_HISTORY_SIZE = 7
    }


    val searchHistoryFlow: Flow<List<String>> = dataStore.data.map { preferences ->
        val jsonString = preferences[HISTORY_KEY] ?: "[]"
        Json.decodeFromString<List<String>>(jsonString)
    }

    suspend fun addSearchTerm(term: String) {
        dataStore.edit { preferences ->
            val jsonString = preferences[HISTORY_KEY] ?: "[]"
            val historyList = Json.decodeFromString<MutableList<String>>(jsonString)

            historyList.remove(term)
            historyList.add(0, term)

            val updatedList = historyList.take(MAX_HISTORY_SIZE)

            preferences[HISTORY_KEY] = Json.encodeToString(updatedList)
        }
    }
}