package com.test.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _signOut = MutableSharedFlow<Unit>()
    val signOut : SharedFlow<Unit> = _signOut.asSharedFlow()

    fun onSignOutClicked() {
        viewModelScope.launch {
            _signOut.emit(Unit)
        }
    }
}