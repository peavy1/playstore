package com.test.myapplication.app

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.test.myapplication.app.fragment.AppCategoryFragment
import com.test.myapplication.app.fragment.AppRankFragment
import com.test.myapplication.app.fragment.AppRecommendFragment

class AppViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabs = listOf(
        AppTab.Recommend(),
        AppTab.Rank(),
        AppTab.Work(),
        AppTab.Kids(),
        AppTab.Category(),
    )

    private val fragments = tabs.map { tab -> tab.getFragment() }

    override fun getItemCount(): Int = tabs.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    fun getTitle(position: Int): Int {
        return tabs[position].titleId
    }
}

