package com.test.myapplication.util

import android.content.Context
import com.test.myapplication.R
import java.math.RoundingMode
import java.text.DecimalFormat

object Formatters {
    fun score(score: Double): String {
        val decimalFormat = DecimalFormat("#.0")
        decimalFormat.roundingMode = RoundingMode.FLOOR
        return decimalFormat.format(score)
    }

    fun ratingCount(rating: Int): String {
        val formatter = DecimalFormat("#,###")
        val formattedString = formatter.format(rating)
        return formattedString
    }

    fun Long.toDownloadTier(context: Context): String {
        val formatter = DecimalFormat("#,###")
        return when {
            this >= 10_000_000_000L -> context.getString(R.string.billion_100)
            this >= 5_000_000_000L -> context.getString(R.string.billion_50)
            this >= 1_000_000_000L -> context.getString(R.string.billion_10)
            this >= 500_000_000L -> context.getString(R.string.billion_5)
            this >= 100_000_000L -> context.getString(R.string.billion_1)
            this >= 50_000_000L -> context.getString(R.string.million_50)
            this >= 10_000_000L -> context.getString(R.string.million_10)
            this >= 5_000_000L -> context.getString(R.string.million_5)
            this >= 1_000_000L -> context.getString(R.string.million_1)
            this >= 500_000L -> context.getString(R.string.five_hs)
            this >= 100_000L -> context.getString(R.string.one_hs)
            this >= 50_000L -> context.getString(R.string.fifty_t)
            this >= 10_000L -> context.getString(R.string.ten_t)
            this >= 5_000L -> "${formatter.format(5_000)}${context.getString(R.string.etc_count)}"
            this >= 1_000L -> "${formatter.format(1_000)}${context.getString(R.string.etc_count)}"
            this >= 500L -> "${formatter.format(500)}${context.getString(R.string.etc_count)}"
            this >= 100L -> "${formatter.format(100)}${context.getString(R.string.etc_count)}"
            this >= 10L -> "${formatter.format(10)}${context.getString(R.string.etc_count)}"
            this >= 5L -> "${formatter.format(5)}${context.getString(R.string.etc_count)}"
            this >= 1L -> "${formatter.format(1)}${context.getString(R.string.etc_count)}"
            else -> context.getString(R.string.none_count)
        }
    }
}