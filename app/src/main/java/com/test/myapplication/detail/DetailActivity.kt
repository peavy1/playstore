package com.test.myapplication.detail

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.test.myapplication.CommonData.items
import com.test.myapplication.R
import com.test.myapplication.model.AppDetails
import com.test.myapplication.util.Constants.EXTRA_APP_ID
import com.test.myapplication.view.detail.AppInfoSection
import com.test.myapplication.view.detail.ReviewSection
import com.test.myapplication.view.detail.TopSection
import com.test.myapplication.view.detail.TopSubSection


class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appId = intent.getStringExtra(EXTRA_APP_ID) ?: ""
            val viewModel = DetailViewModel()
//            viewModel.getDetail(appId)

            DetailScreen(
                appId = appId,
                viewModel = viewModel
            )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    appId: String,
    viewModel: DetailViewModel
) {
    val appDetails by viewModel.appDetails.collectAsState()

    LaunchedEffect(key1 = appId) {
        viewModel.getDetail(appId)
    }
    CollapsingToolbarScreen(details = appDetails)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollapsingToolbarScreen(details: AppDetails?) {
    if(details == null) return
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {   }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = ""
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    scrolledContainerColor = Color.White
                ),
                title = {  },
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
      /*  LazyColumn(
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(100) { index ->
                Text(
                    text = "아이템 #${index + 1}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }*/

        LazyColumn(
            modifier = Modifier.padding(innerPadding).fillMaxSize().background(Color.White),
        ) {
            item {
                TopSection(details)
                TopSubSection(details)
                AppInfoSection(details)
                ReviewSection(details)
            }
        }
    }
}