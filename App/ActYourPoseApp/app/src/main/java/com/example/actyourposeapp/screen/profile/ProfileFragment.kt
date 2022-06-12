package com.example.actyourposeapp.screen.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.actyourposeapp.R
import com.example.actyourposeapp.databinding.FragmentProfileBinding
import com.example.actyourposeapp.screen.home.HomeFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkUser()
    }

    private fun checkUser(){
        val auth = Firebase.auth
        val db = Firebase.firestore

        val user = auth.currentUser
        if(user != null){

            val userData = db.collection("users").document(user.uid)
            userData.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        Log.d(HomeFragment.TAG, "DocumentSnapshot data: ${document.data}")
                        val welcomeUser = "${document.getString("name")}"
                        binding?.tvUsername?.text = welcomeUser
                    } else {
                        Log.d(HomeFragment.TAG, "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(HomeFragment.TAG, "get failed with ", exception)
                }

        }else {
            val defaultText = "User 10001"
            binding?.tvUsername?.text = defaultText
        }
    }
}