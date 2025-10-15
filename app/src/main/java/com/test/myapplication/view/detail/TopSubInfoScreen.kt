package com.test.myapplication.view.detail

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.myapplication.R
import com.test.myapplication.model.AppDetails
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun TopSubSection(details: AppDetails) {
    val context = LocalContext.current

    Row(
        modifier = Modifier.padding(top = 24.dp, bottom = 20.dp)
    ) {
        Column(
            Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment =  Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    text = score(details.score)
                )
                Spacer(modifier = Modifier.width(2.dp))
                Image(
                    painterResource(id = R.drawable.baseline_star_24),
                    contentDescription = "",
                    modifier = Modifier.size(10.dp),
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                fontSize = 12.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                text = details.ratings.toManFormat(context)
            )
        }

        Column(
            modifier = Modifier
                .height(16.dp)
                .size(1.dp)
                .fillMaxSize()
                .align(Alignment.CenterVertically)
                .background(Color.Gray)
        ) {  }

        // 다운로드 수
        Column(
            Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment =  Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    text = details.minInstalls.toDownloadTier(context)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                fontSize = 12.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                text = context.getString(R.string.download)
            )
        }

        Column(
            modifier = Modifier
                .height(16.dp)
                .size(1.dp)
                .fillMaxSize()
                .align(Alignment.CenterVertically)
                .background(Color.Gray)
        ) {  }

        // 연령
        Column(
            Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment =  Alignment.CenterHorizontally
        ) {
            var ageInfo = ""
            AgeRatingIcon(context, details.contentRating) {
                ageInfo = it
            }
            if(ageInfo != context.getString(R.string.age_all)) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    text = ageInfo
                )
            }
        }
    }
}

fun score(score: Double): String {
    val decimalFormat = DecimalFormat("#.0")
    decimalFormat.roundingMode = RoundingMode.FLOOR
    return decimalFormat.format(score)
}

fun Int.toManFormat(context: Context): String {
    val formatter = DecimalFormat("###,###")

    return  context.getString(R.string.review) + when {
        this < 10000 -> formatter.format(this) + context.getString(R.string.quantity)
        else -> {
            val manUnit = this / 10000
            "${formatter.format(manUnit)}${context.getString(R.string.quantity_ten_thousand)}"
        }
    }
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


@Composable
fun AgeRatingIcon(context: Context, rating: String, age: (String) -> Unit) {

    var contentRating = rating.extractLeadingNumber()

    if(contentRating == context.getString(R.string.age_strict)) {
        contentRating = context.getString(R.string.age_strict_num)
    }

    val isDigit = contentRating.any { it.isDigit() }

    age.invoke(rating)

    if(isDigit) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(20.dp)
                .border(width = 1.1.dp, color = Color.Black, shape = CircleShape)
        ) {
            Text(
                text = contentRating,
                color = Color.Black,
                fontSize = 10.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = contentRating,
                modifier = Modifier.padding(vertical = 10.dp),
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }


}

fun String.extractLeadingNumber(): String {
    val numberPart = this.takeWhile { it.isDigit() }
    return numberPart.ifEmpty {
        this
    }
}


