package com.test.myapplication.searchresult

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.test.myapplication.api.RetrofitInstance.api
import com.test.myapplication.model.SearchUiState
import com.test.myapplication.util.SearchHistoryManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SearchPageViewModel(
    private val historyManager: SearchHistoryManager
): ViewModel() {

    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Loading)
    val uiState: StateFlow<SearchUiState> = _uiState

    var query: String = ""

    var screenList: List<String> = emptyList()
    var screenShotIndex = 0

    val searchHistory: StateFlow<List<String>> = historyManager.searchHistoryFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun search(input: String) {
        if(input.isBlank()) return
        _uiState.value = SearchUiState.Loading
        query = input
        viewModelScope.launch {
            try {
                historyManager.addSearchTerm(input)
                _uiState.value = SearchUiState.Success(api.searchApps(input))
            } catch (e: Exception) {
                _uiState.value = SearchUiState.Error
            }

        }
    }
}


class SearchPageViewModelFactory(
    private val historyManager: SearchHistoryManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchPageViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchPageViewModel(historyManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}