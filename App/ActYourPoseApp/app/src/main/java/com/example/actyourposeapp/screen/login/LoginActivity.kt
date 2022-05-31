package com.example.actyourposeapp.screen.login

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.actyourposeapp.databinding.ActivityLoginBinding
import com.example.actyourposeapp.screen.main.MainActivity
import com.example.actyourposeapp.screen.register.RegisterActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding : ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        supportActionBar?.hide()
        addAnimation()

        auth = Firebase.auth

        loginBinding.tvSignup.setOnClickListener {
            val registerIntent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(registerIntent)
        }

        loginBinding.btnLogin.setOnClickListener {
            val email = loginBinding.emailInputText.text.toString()
            val password = loginBinding.passwordInputText.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if(task.isSuccessful){
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        goToMain()
                    }
                }
        }
    }


    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if(currentUser != null){
            goToMain()
        }
    }

    private fun addAnimation(){
        ObjectAnimator.ofFloat(loginBinding.imageView, View.TRANSLATION_Y, -20F, 20F).apply {
            duration = 5000
            repeatMode = ObjectAnimator.REVERSE
            repeatCount = ObjectAnimator.INFINITE
        }.start()
    }

    private fun goToMain(){
        val mainIntent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(mainIntent)
        finish()
    }


    companion object {
        const val TAG = "LoginActivity"
    }
}