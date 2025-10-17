package com.test.myapplication.detail.view

import android.util.Log
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.test.myapplication.R
import com.test.myapplication.model.ReviewItem
import com.test.myapplication.util.AppUtil.createLineSpacingExtraStyle
import com.test.myapplication.util.Constants.STAR_COUNT
import com.test.myapplication.util.Formatters.getDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun ReviewListSection(reviewList: List<ReviewItem>) {

    reviewList.forEach {  item ->
        Column(
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            ReviewCell(item)
        }
    }
}

@Composable
fun ReviewCell(reviewItem: ReviewItem) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier.size(35.dp),
                shape = CircleShape,
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 0.dp
                )
            ) {
                AsyncImage(
                    model = reviewItem.userImage,
                    contentDescription = ""
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = reviewItem.userName,
                fontSize = 14.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color.Black,
            )
        }

        Row(
            modifier = Modifier.padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ReviewItemRatingBar(rating = reviewItem.score)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = getDate(reviewItem.at),
                fontSize = 12.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color.Black,
            )
        }

        Text(
            text = reviewItem.content,
            fontSize = 14.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Light,
            color = Color.Black,
            style = createLineSpacingExtraStyle(fontSize = 14.sp, extraSpacing = 5.sp)
        )

        Spacer(modifier = Modifier.height(28.dp))
    }
}



@Composable
fun ReviewItemRatingBar(
    modifier: Modifier = Modifier,
    rating: Int,
    stars: Int = STAR_COUNT
) {
    val unfilledStars = stars - rating
    Row(modifier = modifier) {

        repeat(rating) {
            Icon(
                modifier = modifier.size(13.dp),
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = null
            )
        }
        repeat(unfilledStars) {
            Icon(
                modifier = modifier.size(13.dp),
                painter = painterResource(R.drawable.ic_star_border),
                contentDescription = null
            )
        }
    }
}