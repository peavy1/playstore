package com.test.myapplication

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.test.myapplication.model.AppItem

object AppUtil {

    fun getJsonFromAssets(context: Context, fileName: String): String? {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    @Composable
    fun Dp.toPx(): Int {
        return with(LocalDensity.current) { this@toPx.toPx() }.toInt()
    }

    fun randomApp(list: List<AppItem>, count: Int): List<AppItem> {
        return list.shuffled().take(count)
    }

}