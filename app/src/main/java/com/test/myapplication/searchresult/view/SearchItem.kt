package com.test.myapplication.searchresult.view

import android.content.Context
import android.content.Intent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.test.myapplication.R
import com.test.myapplication.detail.DetailActivity
import com.test.myapplication.model.AppSummary
import com.test.myapplication.searchresult.SearchPageViewModel
import com.test.myapplication.util.Constants.EXTRA_APP_ID
import com.test.myapplication.util.Formatters.score
import com.test.myapplication.util.Formatters.toDownloadTier

@Composable
fun SearchItem(
    appSummary: AppSummary,
    context: Context,
    navController: NavHostController,
    viewModel: SearchPageViewModel
) {

    var expanded by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Row(
            modifier = Modifier
                .padding(start = 24.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = { offset ->

                        },
                        onTap = { _ ->
                            val intent = Intent(context, DetailActivity::class.java).apply {
                                putExtra(EXTRA_APP_ID, appSummary.appId)
                            }
                            context.startActivity(intent)

                        }
                    )
                }
        ) {

            AsyncImage(
                model = appSummary.icon,
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(72.dp)
                    .padding(end = 14.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Column(
                modifier = Modifier.fillMaxWidth().weight(1f)
            ) {
                Text(
                    text = appSummary.title,
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.W500,
                    color = Color.Black,
                    letterSpacing = (-0.1).sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Text(
                    text = searchAppType(appSummary.developer, appSummary.genre),
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.W300,
                    color = Color.Black,
                    letterSpacing = (-0.1).sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 5.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFF7EFEF))
                            .padding(horizontal = 6.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = score(appSummary.score),
                            fontSize = 10.sp,
                            fontFamily = FontFamily.SansSerif,
                            color = Color.DarkGray,
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Image(
                            painterResource(id = R.drawable.baseline_star_24),
                            contentDescription = "",
                            modifier = Modifier.size(10.dp),
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFF7EFEF))
                            .padding(horizontal = 6.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painterResource(id = R.drawable.ic_download),
                            contentDescription = "",
                            modifier = Modifier.size(10.dp),
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = downloadData(appSummary.installs, context),
                            fontSize = 10.sp,
                            fontFamily = FontFamily.SansSerif,
                            color = Color.DarkGray,
                        )
                    }
                }
            }


            FilledIconButton(
                onClick = { expanded = !expanded },
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = Color(0xFFEEEBF1)
                ),
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = "",
                    modifier = Modifier.size(16.dp)
                )
            }

        }

        if (expanded) {
            SearchResultScreenShotScreen(appSummary.screenshots.take(6), navController, viewModel)
        }


    }

}

fun downloadData(installs: String, context: Context): String {
    if(installs.isEmpty()) return context.getString(R.string.one_count)
    return installs
        .replace("+", "")
        .replace(",", "")
        .toLong()
        .toDownloadTier(context)
}

fun searchAppType(developer: String, genre: String): String {
    return  buildList {
         add(developer)
         add(genre)
    }.joinToString(separator = " • ")
}
