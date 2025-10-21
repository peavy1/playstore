package com.test.myapplication.searchresult

import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.myapplication.api.RetrofitInstance.api
import com.test.myapplication.model.AppSummary
import com.test.myapplication.util.SearchHistoryManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SearchResultViewModel(
    private val historyManager: SearchHistoryManager
): ViewModel() {

    private val _searchApps = MutableStateFlow<List<AppSummary>>(emptyList())
    val searchApps : StateFlow<List<AppSummary>> = _searchApps.asStateFlow()

    var query: String = ""

    val searchHistory: StateFlow<List<String>> = historyManager.searchHistoryFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun search(input: String) {
        Log.d("peavyfffc", input)
        if(input.isBlank()) return
        query = input
        viewModelScope.launch {
            historyManager.addSearchTerm(query)
            _searchApps.value = api.searchApps(input)
        }
    }

    fun clearResults() {
        _searchApps.value = emptyList()
    }
}