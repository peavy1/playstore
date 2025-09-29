package com.test.myapplication.game.viewmodel

import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.myapplication.model.AppItem
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

class GameSharedViewModel: ViewModel() {

    private val _appList = MutableStateFlow<List<AppItem>>(emptyList())
    val appList = _appList.asStateFlow()
//    private val _appList = MutableSharedFlow<List<AppItem>>()
//    val appList = _appList.asSharedFlow()

    private var appListData = mutableListOf<AppItem>()
    private var cachedAppList: List<AppItem>? = null

    fun loadDataIfNeeded(context: Context) {
//        if (cachedAppList != null) {
//            viewModelScope.launch {
//                _appList.emit(cachedAppList!!)
//            }
//            return
//        }

        viewModelScope.launch {
            val jsonData = getJsonFromAssets(context, "list_data.json")
            jsonData?.let {
                val listType = object : TypeToken<List<AppItem>>() {}.type
                val appListData: List<AppItem> = Gson().fromJson(jsonData, listType)
                _appList.emit(appListData)
            }
        }
    }


    fun initListData(context: Context) {
        val jsonData = getJsonFromAssets(context, "list_data.json")
        jsonData?.let {
            val listType = object : TypeToken<List<AppItem>>() {}.type
            appListData = Gson().fromJson(jsonData, listType)
        }
    }


    fun getListData() {
        viewModelScope.launch {
            _appList.emit(appListData)
        }
    }

    private fun getJsonFromAssets(context: Context, fileName: String): String? {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}