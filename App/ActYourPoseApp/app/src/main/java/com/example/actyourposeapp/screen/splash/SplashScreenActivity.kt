package com.example.actyourposeapp.screen.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.actyourposeapp.R
import com.example.actyourposeapp.screen.welcome.WelcomeActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        val delayTimeScreen = 3000

        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashScreenActivity, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }, delayTimeScreen.toLong())
    }

}
