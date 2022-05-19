package com.example.actyourposeapp.screen.login

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.actyourposeapp.databinding.ActivityLoginBinding
import com.example.actyourposeapp.screen.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        supportActionBar?.hide()
        addAnimation()

        loginBinding.tvSignup.setOnClickListener {
            val registerIntent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(registerIntent)
        }
    }

    private fun addAnimation(){
        ObjectAnimator.ofFloat(loginBinding.imageView, View.TRANSLATION_Y, -20F, 20F).apply {
            duration = 5000
            repeatMode = ObjectAnimator.REVERSE
            repeatCount = ObjectAnimator.INFINITE
        }.start()
    }
}