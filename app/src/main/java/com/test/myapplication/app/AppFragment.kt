package com.test.myapplication.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.test.myapplication.R
import com.test.myapplication.databinding.FragmentAppBinding
import com.test.myapplication.game.GameViewPagerAdapter


class AppFragment : Fragment() {

    private var _binding: FragmentAppBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAppBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTabLayout()
    }

    private fun setTabLayout() {
        val tabTitles = listOf("추천", "인기차트", "업무용 앱", "키즈", "카테고리")

        val viewPager = binding.viewPager
        viewPager.adapter = AppViewPagerAdapter(this)
        viewPager.isUserInputEnabled = false

//        viewPager.offscreenPageLimit = tabTitles.size

        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}