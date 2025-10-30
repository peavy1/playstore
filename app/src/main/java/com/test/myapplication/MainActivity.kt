package com.test.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.test.myapplication.databinding.ActivityMainBinding
import com.test.myapplication.login.LoginActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    val profileViewModel: ProfileViewModel by viewModels()
    private val mainViewModel: MainViewModel by viewModels()

    private val googleSignInClient: GoogleSignInClient by lazy {
        (application as PlayStoreApplication).googleSignInClient
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupJetpackNavigation()
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
        initObserve()
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

    private fun initObserve() {
        lifecycleScope.launch {
            mainViewModel.signOut.collectLatest {
                googleSignOut()
            }
        }

    }

    private fun googleSignOut() {
        googleSignInClient.signOut()
        navigateToLoginAndClearStack(this)
    }

    private fun navigateToLoginAndClearStack(context: Context) {
        val intent = Intent(context, LauncherActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        context.startActivity(intent)
    }

}