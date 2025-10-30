package com.test.myapplication

import android.app.Application
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.test.myapplication.util.Constants
import com.test.myapplication.util.DataStoreManager

class PlayStoreApplication : Application() {


    val dataStoreManager: DataStoreManager by lazy {
        DataStoreManager(applicationContext)
    }

    val googleSignInClient: GoogleSignInClient by lazy {
        getGoogleClient()
    }

    private fun getGoogleClient(): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(Constants.GOOGLE_LOGIN_CLIENT_ID)
            .requestEmail()
            .requestProfile()
            .build()

        return GoogleSignIn.getClient(this, gso)
    }

    override fun onCreate() {
        super.onCreate()
    }
}