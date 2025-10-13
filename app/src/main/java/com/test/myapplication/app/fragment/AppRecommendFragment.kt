package com.test.myapplication.app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.test.myapplication.app.AppViewModel
import com.test.myapplication.databinding.FragmentGameItemBinding
import com.test.myapplication.view.CarouselPagerSample
import com.test.myapplication.view.RankView
import com.test.myapplication.view.RecommendLazyRow

class AppRecommendFragment: Fragment() {

    private var _binding: FragmentGameItemBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: AppViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        sharedViewModel.loadDataIfNeeded(requireContext())

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
                        RankView(sharedViewModel.popularFreeApps, sharedViewModel.highestSales, sharedViewModel.popularPaidApps)
                        RecommendLazyRow(sharedViewModel.appListData)
                        Spacer(modifier = Modifier
                            .height(16.dp)
                        )
                    }
                }
            }
        }
    }

}