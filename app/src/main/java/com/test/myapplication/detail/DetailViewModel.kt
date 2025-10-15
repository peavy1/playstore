package com.test.myapplication.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.myapplication.api.RetrofitInstance.api
import com.test.myapplication.model.AppDetails
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {

    private val _appDetails = MutableStateFlow<AppDetails?>(null)
    val appDetails = _appDetails.asStateFlow()

    fun getDetail(appId: String) {
        viewModelScope.launch {
            val details = api.getAppDetails(appId)
            _appDetails.value = details
        }
    }

}