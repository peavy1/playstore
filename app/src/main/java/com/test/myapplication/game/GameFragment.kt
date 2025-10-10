package com.test.myapplication.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.myapplication.databinding.FragmentGameBinding
import com.test.myapplication.game.viewmodel.GameSharedViewModel
import com.test.myapplication.model.AppItem

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        gameSharedViewModel.loadDataIfNeeded(requireContext())
        setTabLayout()

//        val jsonData = getJsonFromAssets("list_data.json")
//        jsonData?.let {
//            val listType = object : TypeToken<List<AppItem>>() {}.type
//            val appList: List<AppItem> = Gson().fromJson(jsonData, listType)
//        }
    }

    private fun setTabLayout() {
        val tabTitles = listOf("추천", "인기 차트", "키즈", "신규", "카테고리")

        val viewPager = binding.viewPager
        viewPager.adapter = GameViewPagerAdapter(this)
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