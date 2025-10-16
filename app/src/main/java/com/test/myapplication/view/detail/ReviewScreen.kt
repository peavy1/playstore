package com.test.myapplication.view.detail

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.size.Size
import com.test.myapplication.R
import com.test.myapplication.model.AppDetails
import com.test.myapplication.util.AppUtil.createLineSpacingExtraStyle
import com.test.myapplication.util.AppUtil.ratingCount
import com.test.myapplication.util.AppUtil.score
import java.text.DecimalFormat
import kotlin.math.ceil
import kotlin.math.floor


@Composable
fun ReviewSection(details: AppDetails) {
    val context = LocalContext.current
    Column {
        ReviewTitle(context)
        ReviewScore(details)
    }
}



@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Float,
    stars: Int = 5
) {
    val filledStars = floor(rating).toInt()
    val halfStar = rating % 1 != 0.0f
    val unfilledStars = (stars - ceil(rating)).toInt()
    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(
                modifier = modifier.size(16.dp),
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = null
            )
        }

        if (halfStar) {
            Icon(
                modifier = modifier.size(16.dp),
                painter = painterResource(R.drawable.ic_star_half),
                contentDescription = null
            )
        }

        repeat(unfilledStars) {
            Icon(
                modifier = modifier.size(16.dp),
                painter = painterResource(R.drawable.ic_star_border),
                contentDescription = null
            )
        }
    }
}

@Composable
fun ReviewScore(details: AppDetails) {
    Row(
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 18.dp)
    ) {
        Column(modifier =Modifier.padding(end = 22.dp)) {
            Text(
                text = score(details.score),
                fontSize = 52.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(2.dp))
            RatingBar(rating = score(details.score).toFloat())
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = ratingCount(details.ratings),
                fontSize = 12.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.5.sp,
                color = Color.Black,
            )
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            val total = details.histogram.fold(0) { total, num -> total + num }
            val percentageList = details.histogram.reversed().map { it.toFloat() / total.toFloat() }
            percentageList.forEachIndexed { index, value ->
                RatingProgressbar(starRating = percentageList.size-index, percentage = value)
            }
        }
    }
}


@Composable
fun RatingProgressbar(
    starRating: Int,
    percentage: Float,
    barColor: Color = colorResource(R.color.select_tab)
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp)
    ) {
        Text(
            text = starRating.toString(),
            color = Color.Black,
            fontWeight = FontWeight.Normal,
        )
        Spacer(modifier = Modifier.width(16.dp))
        Box(
            modifier = Modifier
                .weight(1f)
                .height(8.dp)
                .clip(CircleShape)
                .background(Color.LightGray.copy(alpha = 0.5f))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(percentage)
                    .clip(CircleShape)
                    .background(barColor)
            )
        }
    }
}


@Composable
fun ReviewTitle(context: Context) {
    Column(
        modifier = Modifier.padding(start = 28.dp, end = 12.dp)
    ) {

        Row(
            modifier = Modifier.padding(top = 42.dp, bottom = 12.dp, end = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = context.getString(R.string.review_title),
                modifier = Modifier.weight(1f),
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W200,
                color = Color.Black,
            )

            Card(
                modifier = Modifier.size(35.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFECE3E3)
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 0.dp
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_arrow_forward_24),
                        contentDescription = "",
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

        }


        Text(
            text = context.getString(R.string.review_info),
            modifier = Modifier.padding(end = 6.dp),
            fontSize = 13.sp,
            style = createLineSpacingExtraStyle(fontSize = 13.sp, extraSpacing = 4.sp),
            color = Color.Black,
        )
    }
}