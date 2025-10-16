package com.test.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.test.myapplication.api.RetrofitInstance.api
import com.test.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupJetpackNavigation()
//        fetchAppDetails("com.kakao.talk")
//        fetchReviews("com.kakao.talk")
    }


    fun fetchReviews(appId: String) {
        lifecycleScope.launch {
            try {
                val response = api.getReviews(appId = appId, sortBy = "newest", count = 100)
                Log.d("peavyfffff", response.toString())
            } catch (e: Exception) {
                // 에러 처리
                Log.d("peavyfffff", "fail")
            }
        }
    }

    fun fetchAppDetails(appId: String) {
        lifecycleScope.launch {
            try {
                val details = api.getAppDetails(appId)
                details.icon
                Log.d("peavyaaa", details.toString())
            } catch (e: Exception) {
                Log.d("peavyaaa", "fail")
            }
        }

        lifecycleScope.launch {
            try {
                val result = api.searchApps("지하철")
                Log.d("peavybbb", result.toString())
            } catch (e: Exception) {
                Log.d("peavybbb", "fail")
            }

        }

        lifecycleScope.launch {
            try {
                val editorsChoiceList = api.getEditorsChoice()
                Log.d("peavyddd", editorsChoiceList.toString())
            } catch (e: Exception) {
                Log.d("peavyddd", "fail")
            }
        }


        lifecycleScope.launch {
            try {
                val appList = api.getAppsByCategory("뷰티")
                // 받아온 앱 목록을 LiveData에 담아서 RecyclerView 등에 표시
                Log.d("peavyeee", appList.toString())
            } catch (e: Exception) {

            }
        }

        lifecycleScope.launch {
            try {
                val appList = api.getTopChartByCategory("소셜", "free")
                Log.d("peavyfff", appList.toString())
            } catch (e: Exception) {

            }
        }


        fetchTopCharts("grossing")
    }

    // MainViewModel.kt
    fun fetchTopCharts(chartType: String) {
        lifecycleScope.launch {
            try {
                val chartList = api.getTopCharts(chartType)
                Log.d("peavycc", chartList.toString())
            } catch (e: Exception) {
                Log.d("peavycc", "fail")
            }
        }
    }


    private fun setupJetpackNavigation() {
        val host = supportFragmentManager
            .findFragmentById(R.id.booksearch_nav_host_fragment) as NavHostFragment? ?: return
        navController = host.navController
        binding.bottomNavigationView.setupWithNavController(navController)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.fragment_game, R.id.fragment_app, R.id.fragment_search, R.id.fragment_book
            )
        )

        applyItemForegroundRipple()
        suppressBottomNavLongClick()
    }

    private fun applyItemForegroundRipple() {
        val menuView = binding.bottomNavigationView.getChildAt(0) as BottomNavigationMenuView
        for (i in 0 until menuView.childCount) {
            val itemView = menuView.getChildAt(i) as BottomNavigationItemView
            val ripple = ContextCompat.getDrawable(this, R.drawable.bottom_nav_icon_ripple)
            itemView.background = null
            itemView.foreground = ripple
        }
    }

    private fun suppressBottomNavLongClick() {
        val menuView = binding.bottomNavigationView.getChildAt(0) as BottomNavigationMenuView
        for (i in 0 until menuView.childCount) {
            val itemView = menuView.getChildAt(i) as BottomNavigationItemView
            itemView.setOnLongClickListener {
                true
            }
        }
    }
}