package com.test.myapplication.game.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.test.myapplication.databinding.FragmentGameItemBinding
import com.test.myapplication.game.view.AppListScreen
import com.test.myapplication.game.view.CarouselPagerSample
import com.test.myapplication.game.view.RankView
import com.test.myapplication.game.view.SimpleLazyRow
import com.test.myapplication.game.view.randomApp
import com.test.myapplication.game.viewmodel.GameSharedViewModel


class GameRecommendFragment: Fragment() {

    private var _binding: FragmentGameItemBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: GameSharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedViewModel.loadDataIfNeeded(requireContext())

        return ComposeView(requireContext()).apply {
            setContent {


                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .nestedScroll(rememberNestedScrollInteropConnection())
                ) {

                    item {
                        Spacer(modifier = Modifier
                            .height(16.dp)
                        )

                        CarouselPagerSample()

                        val chuckList1 = randomApp(sharedViewModel.appList.value, 30).chunked(3)
                        val chuckList2 = randomApp(sharedViewModel.appList.value, 30).chunked(3)
                        val chuckList3 = randomApp(sharedViewModel.appList.value, 30).chunked(3)
                        RankView(chuckList1, chuckList2, chuckList3)

                        SimpleLazyRow(sharedViewModel)

                        Spacer(modifier = Modifier
                            .height(16.dp)
                        )
                    }


                }
            }
        }
    }
}