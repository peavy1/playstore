package com.test.myapplication.game.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.test.myapplication.databinding.FragmentGameItemBinding
import com.test.myapplication.view.GameListScreen
import com.test.myapplication.game.GameSViewModel

class GameRankFragment: Fragment() {

    private var _binding: FragmentGameItemBinding? = null
    private val binding get() = _binding!!
private val sharedViewModel: GameSViewModel by activityViewModels()



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