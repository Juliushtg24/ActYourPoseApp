package com.example.actyourposeapp.screen.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.actyourposeapp.databinding.ActivityRegisterBinding
import com.example.actyourposeapp.screen.login.LoginActivity
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {


    private lateinit var registerBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        registerBinding.tvSigning.setOnClickListener {
            backToSignInPage()
        }

        registerBinding.btnRegister.setOnClickListener {
            registerPerson()
        }
    }


    private fun registerPerson() {

        val auth = Firebase.auth

        val emailRegis = registerBinding.emailInputText.text.toString()
        val nameRegis = registerBinding.nameInputText.text.toString()
        val passwordRegis = registerBinding.passwordInputText.text.toString()
        val confidenceLevel = registerBinding.sliderConfidence.value.toInt()


        auth.createUserWithEmailAndPassword(emailRegis, passwordRegis)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    sendToSignInPageWithData(
                        emailRegis,
                        confidenceLevel,
                        nameRegis,
                        passwordRegis,
                    )
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        this, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }


    }


    private fun backToSignInPage() {
        val signIntent = Intent(this@RegisterActivity, LoginActivity::class.java)
        startActivity(signIntent)
        finish()
    }


    private fun sendToSignInPageWithData(
        emailRegis: String,
        confidenceLevel: Int,
        nameRegis: String,
        passwordRegis: String,
    ) {
        val db = Firebase.firestore


        Firebase.auth.signInWithEmailAndPassword(emailRegis, passwordRegis)

        val user = Firebase.auth.currentUser

        val data = hashMapOf(
            "email" to emailRegis,
            "id" to (user?.uid),
            "level" to confidenceLevel,
            "name" to nameRegis,
            "password" to passwordRegis
        )
        Log.d(TAG, "User ID : ${user?.uid}")

        db.collection("users")
            .document(user?.uid.toString())
            .set(data)
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot successfully written!")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }

        Firebase.auth.signOut()

        backToSignInPage()
    }

    companion object {
        const val TAG = "RegisterActivity"
    }
}