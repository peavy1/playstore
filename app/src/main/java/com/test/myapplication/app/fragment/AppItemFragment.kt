package com.test.myapplication.app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.test.myapplication.app.AppSharedViewModel
import com.test.myapplication.databinding.FragmentGameItemBinding
import com.test.myapplication.game.view.AppListScreen
import com.test.myapplication.game.viewmodel.GameSharedViewModel

class AppItemFragment: Fragment() {

    private var _binding: FragmentGameItemBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: AppSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {

            setContent {
                AppListScreen(sharedViewModel = sharedViewModel)
            }
        }
    }

}