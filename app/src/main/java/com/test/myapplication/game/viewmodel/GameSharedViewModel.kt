package com.test.myapplication.game.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.myapplication.AppUtil.getJsonFromAssets
import com.test.myapplication.AppUtil.randomApp
import com.test.myapplication.model.AppItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

class GameSharedViewModel(application: Application): AndroidViewModel(application) {

    private val _appList = MutableStateFlow<List<AppItem>>(emptyList())
    val appList = _appList.asStateFlow()

    private val _appRankList = MutableStateFlow<List<AppItem>>(emptyList())
    val appRankList = _appRankList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    var gameListData = mutableListOf<AppItem>()
    var page = 1
    private var offset = 10
    private var isListEnd = false

    lateinit var chuckList1: List<List<AppItem>>
    lateinit var chuckList2: List<List<AppItem>>
    lateinit var chuckList3: List<List<AppItem>>

    init {
        viewModelScope.launch {
            val jsonData = getJsonFromAssets(application, "game_list_data.json")
            jsonData?.let {
                val listType = object : TypeToken<List<AppItem>>() {}.type
                gameListData = Gson().fromJson(jsonData, listType)
            }

            chuckList1 = randomApp(gameListData, 30).chunked(3)
            chuckList2 = randomApp(gameListData, 30).chunked(3)
            chuckList3 = randomApp(gameListData, 30).chunked(3)
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
            if (endIndex > gameListData.size) {
                endIndex = gameListData.size
                isListEnd = true
            }


            if (startIndex < endIndex) {
                val newItems = gameListData.subList(startIndex, endIndex)
                _appRankList.value = _appRankList.value + newItems
                page++
            }


            _isLoading.value = false
        }
    }
}