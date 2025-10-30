package com.test.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class ProfileViewModel(application: Application): AndroidViewModel(application) {
    private val dataStore = (application as PlayStoreApplication).dataStoreManager

    private val nameFlow = dataStore.loadProfileName
    private val emailFlow = dataStore.loadProfileEmail
    private val imageFlow = dataStore.loadProfileImage

    val uiState: StateFlow<ProfileUiState> = combine(
        nameFlow, emailFlow, imageFlow
    ) { s1, s2, s3 ->
        ProfileUiState(s1, s2, s3)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ProfileUiState()
    )

}

data class ProfileUiState(
    val name: String = "",
    val email: String = "",
    val image: String = ""
)