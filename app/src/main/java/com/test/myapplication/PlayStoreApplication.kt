package com.test.myapplication

import android.app.Application
import com.test.myapplication.util.DataStoreManager

class PlayStoreApplication : Application() {


    val dataStoreManager: DataStoreManager by lazy {
        DataStoreManager(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
    }
}