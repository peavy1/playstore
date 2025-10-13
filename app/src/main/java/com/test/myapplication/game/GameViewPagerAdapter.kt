package com.test.myapplication.game

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class GameViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabs = listOf(
        GameTab.Recommend(),
        GameTab.Rank(),
        GameTab.Work(),
        GameTab.New(),
        GameTab.Category()
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

