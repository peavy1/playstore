package com.test.myapplication.app

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.test.myapplication.app.fragment.AppCategoryFragment
import com.test.myapplication.app.fragment.AppItemFragment
import com.test.myapplication.app.fragment.AppRecommendFragment


class AppViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 5 // 탭 개수

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AppRecommendFragment()
            1 -> AppItemFragment()
            2 -> AppCategoryFragment()
            3 -> AppCategoryFragment()
            4 -> AppCategoryFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}