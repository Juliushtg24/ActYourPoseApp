package com.example.actyourposeapp.screen.welcome

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.actyourposeapp.R
import com.example.actyourposeapp.databinding.FragmentWelcomeTwoBinding
import com.example.actyourposeapp.screen.login.LoginActivity


class WelcomeTwoFragment : Fragment() {

    private var _binding : FragmentWelcomeTwoBinding? = null
    private val binding get() = _binding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWelcomeTwoBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.ivNext?.setOnClickListener{
            val intent = Intent (activity, LoginActivity::class.java)
            activity?.finish()
            activity?.startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}