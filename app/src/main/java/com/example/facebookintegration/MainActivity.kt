package com.example.facebookintegration

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInstaller
import android.content.pm.PackageInstaller.Session
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_main.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*


class MainActivity : AppCompatActivity() {

    val callbackManager = CallbackManager.Factory.create()
    val EMAIL = "email"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            getHashkey(this@MainActivity)


        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired


        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        // If you are using in a fragment, call loginButton.setFragment(this);
// Callback registration
        login_button.setOnClickListener {
            login_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) { // App code

//                    startActivity(Intent(this@MainActivity, SecondAcivity::class.java))
                }

                override fun onCancel() { // App code
                    Toast.makeText(this@MainActivity,"facebook login cancelled ",Toast.LENGTH_SHORT).show()

                }

                override fun onError(exception: FacebookException) { // App code
                    Toast.makeText(this@MainActivity,"facebook error ",Toast.LENGTH_SHORT).show()
                }
            })
        }
        login_button.setReadPermissions(Arrays.asList(EMAIL))
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"))
        FacebookSdk.setAutoInitEnabled(true)
        FacebookSdk.fullyInitialize()
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun loadUserprofile(accessToken: AccessToken) {

    }



    private fun getHashkey(context: Context) {
        try {
            val info = context.packageManager.getPackageInfo(
                context.packageName,
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
                Log.d(("key Hash:"),Base64.encodeToString(md.digest(),Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {
        } catch (e: NoSuchAlgorithmException) {
        }
    }



}
