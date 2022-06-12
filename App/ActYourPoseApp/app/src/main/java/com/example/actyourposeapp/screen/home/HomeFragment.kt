package com.example.actyourposeapp.screen.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.actyourposeapp.api.Topic
import com.example.actyourposeapp.api.response.MsgItem
import com.example.actyourposeapp.databinding.FragmentHomeBinding
import com.example.actyourposeapp.screen.adapter.HomeTipsAdapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {


    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding

    private val homeViewModel by viewModels<HomeViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkUser()

        homeViewModel.topic.observe(viewLifecycleOwner) {
            setTopicsLearn(it)
        }

        homeViewModel.getTopicLearn()
    }


    private fun showListTopic(listTopics: ArrayList<Topic>) {
        binding?.rvTopicList?.adapter = HomeTipsAdapter(listTopics)
        binding?.rvTopicList?.layoutManager = LinearLayoutManager(activity)
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
                        Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                        val welcomeUser = "Welcome ${document.getString("name")}!"
                        binding?.tvWelcomeUser?.text = welcomeUser
                    } else {
                        Log.d(TAG, "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "get failed with ", exception)
                }

        }else {
            val defaultText = "Welcome User!"
            binding?.tvWelcomeUser?.text = defaultText
        }
    }

    private fun setTopicsLearn(topics: List<MsgItem>) {
        val listTopics = ArrayList<Topic>()
        for (topic in topics) {
            val topicItem = Topic(
                topic.title,
                topic.photoUrl,
                topic.refrence,
                topic.description1,
                topic.description2,
                topic.description3
            )
            listTopics.add(topicItem)
        }

        showListTopic(listTopics)
    }

    companion object {
        const val TAG = "HoeFragment"
    }
}