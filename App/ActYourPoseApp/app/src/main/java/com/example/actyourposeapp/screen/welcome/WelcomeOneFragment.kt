package com.example.actyourposeapp.screen.welcome

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.actyourposeapp.R
import com.example.actyourposeapp.databinding.FragmentWelcomeOneBinding


class WelcomeOneFragment : Fragment() {

    private var _binding: FragmentWelcomeOneBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWelcomeOneBinding.inflate(layoutInflater, container,false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding?.ivNext?.setOnClickListener {
            nextFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun nextFragment(){
        Log.d("WelcomeOneFragment", "Clicked")
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        if (transaction != null) {
            transaction.replace(R.id.welcome_fragment, WelcomeTwoFragment())
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
    }


}