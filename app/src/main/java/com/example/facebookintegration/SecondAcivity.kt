package com.example.facebookintegration

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.AccessToken
import com.facebook.login.LoginManager
import kotlinx.android.synthetic.main.activity_second_acivity.*


class SecondAcivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_acivity)


        logout.setOnClickListener {
            LoginManager.getInstance().logOut()
            startActivity(Intent(this@SecondAcivity,MainActivity::class.java))

        }

    }
}
