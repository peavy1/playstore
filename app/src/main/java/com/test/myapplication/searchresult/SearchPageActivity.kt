package com.test.myapplication.searchresult

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.myapplication.searchresult.view.SearchInPut
import com.test.myapplication.searchresult.view.SearchResultScreen
import com.test.myapplication.searchresult.view.SearchScreenShotSlide
import com.test.myapplication.util.SearchHistoryManager


class SearchPageActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val factory = SearchPageViewModelFactory(SearchHistoryManager(this))
            val viewModel = viewModel<SearchPageViewModel>(factory = factory)
            Navigation(viewModel) {
                finish()
            }
        }
    }
}

@Composable
fun Navigation(
    viewModel: SearchPageViewModel,
    onCloseActivity: () -> Unit
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = SearchPageType.Input.route) {
        composable(route = SearchPageType.Input.route) {  SearchInPut(viewModel, navController) {
            onCloseActivity.invoke()
        } }
        composable(route = SearchPageType.Result.route) {  SearchResultScreen(viewModel, navController) {
            navController.navigateUp()
        }  }
        composable(route = SearchPageType.Screen.route) {   SearchScreenShotSlide(viewModel.screenList, viewModel.screenShotIndex)  }
    }
}





