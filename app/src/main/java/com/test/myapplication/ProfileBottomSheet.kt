package com.test.myapplication

import android.content.Context
import android.text.Spanned
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.test.myapplication.searchresult.SearchPageType
import kotlinx.coroutines.launch


@Composable
fun ProfileSheetContent(
    context: Context,
    onCloseClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(id = R.drawable.ic_close),
                contentDescription = "",
                modifier = Modifier.size(24.dp).clickable {
                    onCloseClick.invoke()
                },
            )

            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Center
            ) {
                GoogleLogoText()
            }
        }

        //
        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(8.dp),
        ) {
             Row(
                verticalAlignment = Alignment.CenterVertically
             ) {
                 AsyncImage(
                     model = "https://lh3.googleusercontent.com/a/ACg8ocLx6yurUy_G5qqjPH5swJjD1IzGyVqkGDQf0nj81iugzVhL7Q=s96-c",
                     contentDescription = "",
                     modifier = Modifier
                         .padding(end = 15.dp)
                         .size(40.dp)
                         .clip(RoundedCornerShape(200.dp))
                 )

                 Column(
                     modifier = Modifier.weight(1f)
                 ) {
                     Text(
                         text = "Peavy Jung",
                         fontSize = 14.sp,
                         fontWeight = FontWeight.SemiBold,
                         modifier = Modifier.padding(bottom = 2.dp)
                     )

                     Text(
                         text = "peavy@culturehero.net",
                         fontSize = 12.sp
                     )
                 }
             }

//
            OutlinedButton(
                onClick = { },
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color.Black),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                contentPadding = PaddingValues(
                    horizontal = 18.dp,
                    vertical = 0.dp
                ),
                modifier = Modifier
                    .padding(start = 52.dp, top = 15.dp)
                    .height(30.dp)
            ) {
                Text(
                    text = context.getString(R.string.management_account),
                    fontSize = 13.sp
                )
            }

            Column(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .background(colorResource(R.color.black))
                    .height(0.5.dp)
            ) {

            }

            MenuInner(context = context)
        }


        MenuOuter(context)

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = stringResource(R.string.profile_bottom_info),
            color = Color.Black,
            fontSize = 11.sp
        )

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun MenuInner(context: Context) {

    val menuList = listOf(
        context.getString(R.string.menu_app_management) to R.drawable.baseline_tab_24,
        context.getString(R.string.menu_alarm) to R.drawable.baseline_notifications_none_24,
        context.getString(R.string.menu_payment) to R.drawable.ic_payment,
        context.getString(R.string.menu_product) to R.drawable.ic_security,
        context.getString(R.string.menu_library) to R.drawable.ic_folder_open,
        context.getString(R.string.menu_play_point) to R.drawable.ic_location
    )

    menuList.forEach {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(
                        color = Color.Gray.copy(alpha = 0.1f)
                    ),
                    onClick = {

                    }
                )
                .padding(15.dp)
        ) {
            Image(
                painterResource(it.second),
                contentDescription = "",
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = it.first,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
    }
}

@Composable
fun MenuOuter(context: Context) {
    val menuList = listOf(
        context.getString(R.string.menu_setting) to R.drawable.ic_pin_circle,
        context.getString(R.string.menu_customer_service) to R.drawable.ic_settings,
        context.getString(R.string.management_account) to R.drawable.ic_info_outline
    )

    menuList.forEach {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(
                        color = Color.Gray.copy(alpha = 0.1f)
                    ),
                    onClick = {

                    }
                )
                .padding(15.dp)
        ) {
            Image(
                painterResource(it.second),
                contentDescription = "",
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = it.first,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
    }
}


@Composable
fun GoogleLogoText() {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xFF4285F4))) {
            append("G")
        }
        withStyle(style = SpanStyle(color = Color(0xFFDB4437))) {
            append("o")
        }
        withStyle(style = SpanStyle(color = Color(0xFFF4B400))) {
            append("o")
        }
        withStyle(style = SpanStyle(color = Color(0xFF4285F4))) {
            append("g")
        }
        withStyle(style = SpanStyle(color = Color(0xFF0F9D58))) {
            append("l")
        }
        withStyle(style = SpanStyle(color = Color(0xFFDB4437))) {
            append("e")
        }
    }

    Text(
        text = annotatedString,
        fontSize = 20.sp
    )
}



