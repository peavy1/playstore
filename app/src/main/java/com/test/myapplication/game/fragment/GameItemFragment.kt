package com.test.myapplication.game.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.test.myapplication.databinding.FragmentGameItemBinding
import com.test.myapplication.game.view.AppListScreen
import com.test.myapplication.game.view.GameListScreen
import com.test.myapplication.game.viewmodel.GameSharedViewModel
import kotlinx.coroutines.delay

class GameItemFragment: Fragment() {

    private var _binding: FragmentGameItemBinding? = null
    private val binding get() = _binding!!
private val sharedViewModel: GameSharedViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {

            setContent {
                GameListScreen(sharedViewModel)

            }
        }
    }

}