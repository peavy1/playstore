package com.test.myapplication.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.test.myapplication.MainActivity
import com.test.myapplication.PlayStoreApplication
import com.test.myapplication.ProfileViewModel
import com.test.myapplication.R
import com.test.myapplication.util.Constants
import com.test.myapplication.util.DataStoreManager
import kotlinx.coroutines.launch

class LoginActivity: AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    private val googleSignInClient: GoogleSignInClient by lazy {
        (application as PlayStoreApplication).googleSignInClient
    }

    private val googleAuthLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

        try {
            val account = task.getResult(ApiException::class.java)

            val userName = account.displayName ?: ""
            val userEmail = account.email ?: ""
            val userProfileImage = account.photoUrl.toString()

            loginViewModel.saveProfile(userName, userEmail, userProfileImage)

            Log.d("GoogleLogin", "Success: $userName / $userEmail")
            Log.d("GoogleLogin", "Photo URL: $userProfileImage")

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        } catch (e: ApiException) {
            Log.e("GoogleLogin", "Failed: ${e.statusCode} / ${e.message}")
            Log.e("GoogleLogin", "Stacktrace: ", e)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            LoginView {
                requestGoogleLogin()
            }
        }
    }

    @Composable
    fun LoginView(
        onLoginClick: () -> Unit
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment =  Alignment.CenterHorizontally
        ) {

            Image(
                painterResource(id = R.drawable.google_play_logo),
                contentDescription = "",
                modifier = Modifier.size(200.dp),
            )
            Spacer(
                modifier = Modifier.height(0.dp)
            )
            Text(
                text = getString(R.string.login_info),
                fontSize = 12.sp,
                color = Color.Black,
                letterSpacing = 0.5.sp
            )
            Spacer(
                modifier = Modifier.height(36.dp)
            )
            Button(
                onClick = { onLoginClick.invoke() },
                modifier = Modifier.width(138.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.select_tab),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = getString(R.string.login),
                    fontSize = 14.sp
                )
            }


        }
    }


    private fun requestGoogleLogin() {
        val signInIntent = googleSignInClient.signInIntent
        googleAuthLauncher.launch(signInIntent)
    }
}