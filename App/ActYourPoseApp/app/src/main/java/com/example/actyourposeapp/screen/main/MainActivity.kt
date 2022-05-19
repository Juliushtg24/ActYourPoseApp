package com.example.actyourposeapp.screen.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.actyourposeapp.databinding.ActivityMainBinding
import com.example.actyourposeapp.screen.splash.SplashScreenActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loginIntent = Intent(this@MainActivity, SplashScreenActivity::class.java)
        startActivity(loginIntent)
    }
}