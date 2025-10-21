package com.test.myapplication.searchresult

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.test.myapplication.R


sealed class SearchPageType(
    val route: String
) {
    object Home: SearchPageType(
        route = "input",
    )

    object Result: SearchPageType(
        route = "result"
    )
}