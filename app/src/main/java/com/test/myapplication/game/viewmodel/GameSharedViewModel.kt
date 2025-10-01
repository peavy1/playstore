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
import com.test.myapplication.game.view.randomApp
import com.test.myapplication.model.AppItem
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

class GameSharedViewModel(application: Application): AndroidViewModel(application) {

    private val _appList = MutableStateFlow<List<AppItem>>(emptyList())
    val appList = _appList.asStateFlow()

    var appListData = mutableListOf<AppItem>()
    private var cachedAppList: List<AppItem>? = null

    lateinit var chuckList1: List<List<AppItem>>
    lateinit var chuckList2: List<List<AppItem>>
    lateinit var chuckList3: List<List<AppItem>>

    init {
        viewModelScope.launch {
            val jsonData = getJsonFromAssets(application, "list_data.json")
            jsonData?.let {
                val listType = object : TypeToken<List<AppItem>>() {}.type
                appListData= Gson().fromJson(jsonData, listType)
            }

            chuckList1 = randomApp(appListData, 30).chunked(3)
            chuckList2 = randomApp(appListData, 30).chunked(3)
            chuckList3 = randomApp(appListData, 30).chunked(3)
        }
    }

    fun loadDataIfNeeded(context: Context) {

//        viewModelScope.launch {
//            val jsonData = getJsonFromAssets(context, "list_data.json")
//            jsonData?.let {
//                val listType = object : TypeToken<List<AppItem>>() {}.type
//                val appListData: List<AppItem> = Gson().fromJson(jsonData, listType)
//                _appList.emit(appListData)
//            }
//        }

//        viewModelScope.launch {
//            _appList.emit(appListData)
//        }
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