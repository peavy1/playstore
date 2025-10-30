package com.test.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.test.myapplication.util.DataStoreManager
import kotlinx.coroutines.launch

class LoginActivity: AppCompatActivity() {

    private val dataStoreManager: DataStoreManager by lazy {
        (application as PlayStoreApplication).dataStoreManager
    }

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

            val userName = account.displayName ?: ""
            val userEmail = account.email ?: ""
            val userProfilePic = account.photoUrl.toString()

            lifecycleScope.launch {
                dataStoreManager.saveProfileName(userName)
                dataStoreManager.saveProfileEmail(userEmail)
                dataStoreManager.saveProfileImage(userProfilePic)
            }

            Log.d("GoogleLogin", "Success: $userName / $userEmail")
            Log.d("GoogleLogin", "Photo URL: $userProfilePic")

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

           /* AsyncImage(
                model = LOGO_URL,
                contentDescription = "",
                modifier = Modifier.size(200.dp)
            )*/
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
}