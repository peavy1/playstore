package com.test.myapplication.detail.view

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.myapplication.R
import com.test.myapplication.model.AppDetails
import com.test.myapplication.util.AppUtil.extractLeadingNumber
import com.test.myapplication.util.Formatters.score
import com.test.myapplication.util.Formatters.toDownloadTier
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
                    modifier = Modifier.size(14.dp),
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



