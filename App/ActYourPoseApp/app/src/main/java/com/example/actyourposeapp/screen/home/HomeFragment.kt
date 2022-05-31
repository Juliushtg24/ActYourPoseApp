package com.example.actyourposeapp.screen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.actyourposeapp.databinding.FragmentHomeBinding
import com.example.actyourposeapp.screen.adapter.HomeTipsAdapter

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding

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

        showListTopic()
    }


    private fun showListTopic(){
        binding?.rvTopicList?.adapter = HomeTipsAdapter()
        binding?.rvTopicList?.layoutManager = LinearLayoutManager(activity)
    }
}