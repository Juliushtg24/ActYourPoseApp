package com.example.actyourposeapp.screen.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.MutableData

class LoginViewModel : ViewModel() {

    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin : LiveData<Boolean> = _isLogin

    fun getUserAuth(email: String, password: String, auth: FirebaseAuth) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            _isLogin.value = it.isSuccessful
        }
    }
}