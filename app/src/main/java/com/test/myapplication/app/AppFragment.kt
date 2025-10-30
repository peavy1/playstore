package com.test.myapplication.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.tabs.TabLayoutMediator
import com.test.myapplication.MainActivity
import com.test.myapplication.ProfileBottomSheetFragment
import com.test.myapplication.ProfileViewModel
import com.test.myapplication.databinding.FragmentAppBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class AppFragment : Fragment() {

    private var _binding: FragmentAppBinding? = null
    private val binding get() = _binding!!
    private lateinit var profileViewModel: ProfileViewModel

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

        profileViewModel = (activity as MainActivity).viewModel

        binding.tabSection.icProfile.setOnClickListener {
            val bottomSheet = ProfileBottomSheetFragment()
            bottomSheet.show(parentFragmentManager, "ProfileBottomSheetTag")
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                profileViewModel.uiState.collectLatest { uiState ->
                    binding.tabSection.icProfile.load(uiState.image) {
                        transformations(CircleCropTransformation())
                        crossfade(true)
                    }
                }
            }
        }
    }

    private fun setTabLayout() {
        val appViewPagerAdapter = AppViewPagerAdapter(this)
        val viewPager = binding.viewPager
        viewPager.adapter = appViewPagerAdapter
        viewPager.isUserInputEnabled = false
        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setText(appViewPagerAdapter.getTitle(position))
        }.attach()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}