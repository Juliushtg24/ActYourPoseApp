package com.example.actyourposeapp.screen.login

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.actyourposeapp.databinding.ActivityLoginBinding
import com.example.actyourposeapp.screen.main.MainActivity
import com.example.actyourposeapp.screen.register.RegisterActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding : ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)


        auth = Firebase.auth
        supportActionBar?.hide()
        addAnimation()

        loginViewModel.isLogin.observe(this){
            setLoginState(it)
        }

        loginBinding.tvSignup.setOnClickListener {
            goToRegister()
        }

        loginBinding.btnLogin.setOnClickListener {
            val email = loginBinding.emailInputText.text.toString()
            val password = loginBinding.passwordInputText.text.toString()

            when {
                email.isEmpty() -> {
                    loginBinding.emailEditTextLayout.error = "Email is Empty"
                    loginBinding.emailEditTextLayout.requestFocus()
                }
                password.isEmpty() -> {
                    loginBinding.passwordEditTextLayout.error = "Password is Empty"
                    loginBinding.passwordEditTextLayout.requestFocus()
                }
                password.length < 6 -> {
                    loginBinding.passwordEditTextLayout.error = "Password Must be greater than 6"
                    loginBinding.passwordEditTextLayout.requestFocus()
                }
                else -> {
                    loginViewModel.getUserAuth(email, password, auth)
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

        AlertDialog.Builder(this).apply {
            setTitle("Yeah!")
            setMessage("You Now Login Ready to go in?")
            setPositiveButton("Next") { _, _ ->
                mainIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(mainIntent)
                finish()
            }
            create()
            show()
        }


    }

    private fun goToRegister(){
        val registerIntent = Intent(this@LoginActivity, RegisterActivity::class.java)
        startActivity(registerIntent)
    }

    private fun setLoginState(isLogin: Boolean){
        if(isLogin){

            goToMain()
        }else{
            Toast.makeText(this@LoginActivity, "Failed Login", Toast.LENGTH_SHORT).show()
        }
    }

}