package com.test.myapplication.searchresult.view

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.test.myapplication.R
import com.test.myapplication.searchresult.SearchPageViewModel
import com.test.myapplication.searchresult.autoFocusOnShow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.test.myapplication.detail.DetailActivity
import com.test.myapplication.model.SearchUiState
import com.test.myapplication.searchresult.SearchPageType
import com.test.myapplication.util.Constants.EXTRA_APP_ID
import com.test.myapplication.util.Formatters.score
import com.test.myapplication.util.Formatters.toDownloadTier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultScreen(
    viewModel: SearchPageViewModel,
    navController: NavHostController
) {

    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { }) {
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
                title = {
                    TextField(
                        value = viewModel.query,
                        textStyle = TextStyle(fontSize = 18.sp),
                        onValueChange = {  },
                        placeholder = {  },
                        singleLine = true,
                        readOnly = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                        ),
                        modifier = Modifier.autoFocusOnShow()
                    )
                }
            )
        },
    ) { innerPadding ->

        val context = LocalContext.current

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {

            when (val state = uiState) {

                is SearchUiState.Loading -> {
                    CircularProgressIndicator()
                }

                is SearchUiState.Success -> {
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items(
                            items = state.apps,
                            key = { app ->
                                app.appId ?: app.developer
                            }
                        ) { app ->
                            val searchAppType = searchAppType(app.developer, app.genre)
                            val score = score(app.score)
                            val downloadData = downloadData(app.installs, context)
                            var expanded by rememberSaveable(app.appId) { mutableStateOf(false) }
                            SearchItem(
                                appSummary = app,
                                searchAppType= searchAppType,
                                score = score,
                                downloadData = downloadData,
                                expanded = expanded,
                                onItemClick = { appId ->
                                    val intent = Intent(context, DetailActivity::class.java).apply {
                                        putExtra(EXTRA_APP_ID, appId)
                                    }
                                    context.startActivity(intent)
                                },
                                onExpandClick = { expanded = !expanded },
                                onScreenshotClick = { index ->
                                    viewModel.screenList = app.screenshots.take(6)
                                    viewModel.screenShotIndex = index
                                    navController.navigate(SearchPageType.Screen.route)
                                }
                            )
                        }
                    }
                }

                is SearchUiState.Error -> {
                    Text(text = context.getString(R.string.search_request_error))
                }
            }
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