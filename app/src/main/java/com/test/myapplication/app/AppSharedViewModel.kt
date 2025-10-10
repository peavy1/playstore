package com.test.myapplication.app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.myapplication.AppUtil
import com.test.myapplication.AppUtil.randomApp
import com.test.myapplication.model.AppItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppSharedViewModel(application: Application): AndroidViewModel(application) {

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

    lateinit var chuckList1: List<List<AppItem>>
    lateinit var chuckList2: List<List<AppItem>>
    lateinit var chuckList3: List<List<AppItem>>

    init {
        viewModelScope.launch {
            val jsonData = AppUtil.getJsonFromAssets(application, "list_data.json")
            jsonData?.let {
                val listType = object : TypeToken<List<AppItem>>() {}.type
                appListData = Gson().fromJson(jsonData, listType)
            }

            chuckList1 = randomApp(appListData, 30).chunked(3)
            chuckList2 = randomApp(appListData, 30).chunked(3)
            chuckList3 = randomApp(appListData, 30).chunked(3)
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
                _appRankList.value = _appRankList.value + newItems
                page++
            }


            _isLoading.value = false
        }
    }
}