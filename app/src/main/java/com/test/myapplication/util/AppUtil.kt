package com.test.myapplication.util

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.test.myapplication.game.GameFragment
import com.test.myapplication.model.AppItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.math.RoundingMode
import java.text.DecimalFormat

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

    fun <T> randomApp(list: List<T>, count: Int): List<T> {
        return list.shuffled().take(count)
    }

    fun createLineSpacingExtraStyle(fontSize: TextUnit, extraSpacing: TextUnit): TextStyle {
        return TextStyle(
            fontSize = fontSize,
            lineHeight = (fontSize.value + extraSpacing.value).sp
        )
    }

    fun String.extractLeadingNumber(): String {
        val numberPart = this.takeWhile { it.isDigit() }
        return numberPart.ifEmpty {
            this
        }
    }


}