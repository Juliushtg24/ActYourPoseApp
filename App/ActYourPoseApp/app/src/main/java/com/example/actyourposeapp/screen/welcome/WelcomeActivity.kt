package com.example.actyourposeapp.screen.welcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.actyourposeapp.R
import com.example.actyourposeapp.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.welcome_fragment, WelcomeOneFragment(), "WelcomeOneFragment")
                .commitAllowingStateLoss()
        }

    }
}