package com.test.myapplication.book

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.myapplication.AppUtil
import com.test.myapplication.model.AppItem
import kotlinx.coroutines.launch

class BookSharedViewModel(application: Application): AndroidViewModel(application) {

    var bookListData = mutableListOf<AppItem>()

    init {
        viewModelScope.launch {
            val jsonData = AppUtil.getJsonFromAssets(application, "book_list.json")
            jsonData?.let {
                val listType = object : TypeToken<List<AppItem>>() {}.type
                bookListData = Gson().fromJson(jsonData, listType)
            }
        }
    }
}