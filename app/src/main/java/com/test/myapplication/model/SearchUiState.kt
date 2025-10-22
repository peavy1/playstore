package com.test.myapplication.model

sealed interface SearchUiState {
    object Loading : SearchUiState
    data class Success(val apps: List<AppSummary>) : SearchUiState
    object Error : SearchUiState
}