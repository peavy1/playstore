package com.test.myapplication.app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.myapplication.util.AppUtil
import com.test.myapplication.util.AppUtil.randomApp
import com.test.myapplication.model.AppItem
import com.test.myapplication.util.Constants.APP_LIST_JSON
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppViewModel(application: Application): AndroidViewModel(application) {

    private val _appList = MutableStateFlow<List<AppItem>>(emptyList())
    val appList = _appList.asStateFlow()

    private val _appRankList = MutableStateFlow<List<AppItem>>(emptyList())
    val appRankList = _appRankList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    var appListData = mutableListOf<AppItem>()
    var page = 1
    private var offset = 10
    private var isListEnd = false

    lateinit var popularFreeApps: List<List<AppItem>>
    lateinit var highestSales: List<List<AppItem>>
    lateinit var popularPaidApps: List<List<AppItem>>

    init {
        viewModelScope.launch {
            val jsonData = AppUtil.getJsonFromAssets(application, APP_LIST_JSON)
            jsonData?.let {
                val listType = object : TypeToken<List<AppItem>>() {}.type
                appListData = Gson().fromJson(jsonData, listType)
            }

            popularFreeApps = randomApp(appListData, 30).chunked(3)
            highestSales = randomApp(appListData, 30).chunked(3)
            popularPaidApps = randomApp(appListData, 30).chunked(3)
        }
    }

    fun loadNextPage() {

        if (_isLoading.value || isListEnd) return

        viewModelScope.launch {
            _isLoading.value = true


            if (_appRankList.value.isNotEmpty()) {
                delay(500)
            }

            val startIndex = (page - 1) * offset
            var endIndex = startIndex + offset
            if (endIndex > appListData.size) {
                endIndex = appListData.size
                isListEnd = true
            }


            if (startIndex < endIndex) {
                val newItems = appListData.subList(startIndex, endIndex)
                _appRankList.value += newItems
                page++
            }


            _isLoading.value = false
        }
    }
}