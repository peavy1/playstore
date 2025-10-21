package com.test.myapplication.detail.view

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.test.myapplication.R
import com.test.myapplication.model.AppDetails
import com.test.myapplication.util.AppUtil.createLineSpacingExtraStyle

@Composable
fun AppInfoSection(details: AppDetails) {
    val context = LocalContext.current
    Column {
        Spacer(modifier = Modifier.height(4.dp))
        Install(context)
        Spacer(modifier = Modifier.height(14.dp))
        screenShot(context, details.screenshots.take(6))
        AppInfo(context, details.summary)
        SecurityInfo(context)
    }

}

@Composable
fun AppInfo(context: Context, summary: String) {
    Column(
        modifier = Modifier.padding(start = 28.dp, end = 12.dp)
    ) {
        Text(
            text = context.getString(R.string.app_info),
            modifier = Modifier.padding(top = 24.dp, bottom = 12.dp),
            fontSize = 20.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.W200,
            color = Color.Black,
        )

        Text(
            text = summary,
            fontSize = 14.sp,
            color = Color.Black,
        )
    }
}

@Composable
fun SecurityInfo(context: Context) {
    Column(
        modifier = Modifier.padding(start = 28.dp, end = 32.dp)
    ) {
        Text(
            text = context.getString(R.string.security_title),
            modifier = Modifier.padding(top = 24.dp, bottom = 12.dp),
            fontSize = 20.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.W200,
            color = Color.Black,
        )

        Text(
            text = context.getString(R.string.security_info),
            fontSize = 14.sp,
            style = createLineSpacingExtraStyle(fontSize = 14.sp, extraSpacing = 4.sp),
            color = Color.Black,
        )
    }
}


@Composable
fun screenShot(context: Context, screenList: List<String>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 28.dp)
    ) {

        items(screenList) { imageUrl ->
            AsyncImage(
                model = imageUrl,
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(90.dp)
//                    .shadow(2.dp, RoundedCornerShape(9.dp))
                    .aspectRatio(1.0f / 2f)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
    }
}

@Composable
fun Install(context: Context) {

    Button(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .height(42.dp),
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.select_tab), // 배경색
            contentColor = Color.White
        )
    ) {
        Text(
            text = context.getString(R.string.install),
            fontSize = 14.sp
        )
    }
}

