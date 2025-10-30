package com.test.myapplication.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.tabs.TabLayoutMediator
import com.test.myapplication.ProfileBottomSheetFragment
import com.test.myapplication.databinding.FragmentGameBinding

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
        setTabLayout()

        binding.tabSection.icProfile.setOnClickListener {
            val bottomSheet = ProfileBottomSheetFragment()
            bottomSheet.show(parentFragmentManager, "ProfileBottomSheetTag")
        }

        binding.tabSection.icProfile.load("https://lh3.googleusercontent.com/a/ACg8ocLFDtq_ejWiqRzKNMo21-2M1gIW20oocH2zZLrZknHHQ0ccTw=s96-c") {
            transformations(CircleCropTransformation())
            crossfade(true)
        }
    }

    private fun setTabLayout() {
        val gameViewPagerAdapter = GameViewPagerAdapter(this)
        val viewPager = binding.viewPager
        viewPager.adapter = gameViewPagerAdapter
        viewPager.isUserInputEnabled = false

        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setText(gameViewPagerAdapter.getTitle(position))
        }.attach()
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}