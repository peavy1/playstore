package com.test.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.test.myapplication.api.RetrofitInstance.api
import com.test.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val googleSignInClient: GoogleSignInClient by lazy {
        getGoogleClient()
    }
    private val googleAuthLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

        try {
            val account = task.getResult(ApiException::class.java)
            val idToken = account.idToken

            val userName = account.displayName
            val userEmail = account.email
            val userProfilePic = account.photoUrl

            Log.d("GoogleLogin", "Success: $userName / $userEmail")
            Log.d("GoogleLogin", "Photo URL: $userProfilePic")

        } catch (e: ApiException) {
            Log.e("GoogleLogin", "Failed: ${e.statusCode} / ${e.message}")
            Log.e("GoogleLogin", "Stacktrace: ", e)
        }
    }


    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupJetpackNavigation()
        checkLoginStatus()
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

    private fun getGoogleClient(): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.google_login_client_id))
            .requestEmail()
            .requestProfile()
            .build()

        return GoogleSignIn.getClient(this, gso)
    }

    private fun requestGoogleLogin() {
        val signInIntent = googleSignInClient.signInIntent
        googleAuthLauncher.launch(signInIntent)
    }

    private fun checkLoginStatus() {
        val account = GoogleSignIn.getLastSignedInAccount(this)

        if (account != null) {
            Log.d("GoogleLogin", "Already signed in: ${account.photoUrl}")

            googleSignInClient.signOut()

        } else {
            Log.d("GoogleLogin", "Not signed in")
            requestGoogleLogin()
        }
    }
}