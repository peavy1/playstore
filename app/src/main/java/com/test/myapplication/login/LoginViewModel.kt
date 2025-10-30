package com.test.myapplication.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.myapplication.PlayStoreApplication
import kotlinx.coroutines.launch

class LoginViewModel(application: Application): AndroidViewModel(application) {
    private val dataStore = (application as PlayStoreApplication).dataStoreManager

    fun saveProfile(
        userName: String,
        userEmail: String,
        userProfilePic: String
    ) {
        viewModelScope.launch {
            dataStore.saveProfileName(userName)
            dataStore.saveProfileEmail(userEmail)
            dataStore.saveProfileImage(userProfilePic)
        }
    }

}