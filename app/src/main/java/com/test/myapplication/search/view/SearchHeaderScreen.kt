package com.test.myapplication.search.view

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.test.myapplication.profilebottomsheet.ProfileBottomSheetFragment
import com.test.myapplication.ProfileViewModel
import com.test.myapplication.R
import com.test.myapplication.searchresult.SearchPageActivity


@Composable
fun SearchHeader(
    viewModel: ProfileViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SearchBar(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(16.dp))
        ProfileIcon(uiState.image) {
            val fragmentManager = (context as? FragmentActivity)?.supportFragmentManager

            fragmentManager?.let {
                ProfileBottomSheetFragment().show(it, "ProfileBottomSheetTag")
            }
        }



    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0xFFF7EFEF))
            .padding(horizontal = 12.dp, vertical = 14.dp).pointerInput(Unit) {
                detectTapGestures(
                    onTap = { _ ->
                        val intent = Intent(context, SearchPageActivity::class.java)
                        context.startActivity(intent)
                    }
                )
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(id = R.drawable.ic_search),
            contentDescription = "",
            modifier = Modifier.size(20.dp),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(R.string.search_placeholder),
            fontSize = 15.sp,
            fontFamily = FontFamily.SansSerif,
            color = Color.DarkGray,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            painterResource(id = R.drawable.baseline_keyboard_voice_24),
            contentDescription = "",
            modifier = Modifier.size(20.dp),
        )
    }
}

@Composable
fun ProfileIcon(profileImage: String, click:  () -> Unit) {
    AsyncImage(
        model = profileImage,
        contentDescription = "",
        modifier = Modifier
            .size(35.dp)
            .clip(RoundedCornerShape(200.dp))
            .clickable {
                click.invoke()
            }
    )
}

