package com.test.myapplication.game

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.test.myapplication.game.fragment.GameItemFragment
import com.test.myapplication.game.fragment.GamePopularFragment
import com.test.myapplication.game.fragment.GameRecommendFragment

class GameViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 5 // 탭 개수

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> GameRecommendFragment()
            1 -> GameItemFragment()
            2 -> GamePopularFragment()
            3 -> GamePopularFragment()
            4 -> GamePopularFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}