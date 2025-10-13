package com.test.myapplication.app

import androidx.fragment.app.Fragment
import com.test.myapplication.R
import com.test.myapplication.app.fragment.AppCategoryFragment
import com.test.myapplication.app.fragment.AppKidsFragment
import com.test.myapplication.app.fragment.AppRankFragment
import com.test.myapplication.app.fragment.AppRecommendFragment
import com.test.myapplication.app.fragment.AppWorkFragment

sealed class AppTab(
    val titleId: Int
) {
    abstract fun getFragment(): Fragment

    class Recommend: AppTab(R.string.app_tab_recommend) {
        override fun getFragment() = AppRecommendFragment()
    }

    class Rank: AppTab(R.string.app_tab_popular) {
        override fun getFragment() = AppRankFragment()
    }

    class Work: AppTab(R.string.app_tab_work) {
        override fun getFragment() = AppWorkFragment()
    }

    class Kids: AppTab(R.string.app_tab_kids) {
        override fun getFragment() = AppKidsFragment()
    }

    class Category: AppTab(R.string.app_tab_category) {
        override fun getFragment() = AppCategoryFragment()
    }
}