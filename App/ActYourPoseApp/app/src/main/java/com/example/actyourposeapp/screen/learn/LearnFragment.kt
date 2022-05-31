package com.example.actyourposeapp.screen.learn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.actyourposeapp.databinding.FragmentLearnBinding
import com.example.actyourposeapp.databinding.FragmentProfileBinding


class LearnFragment : Fragment() {

    private var _binding: FragmentLearnBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLearnBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }


}