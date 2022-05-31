package com.example.actyourposeapp.screen.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.actyourposeapp.R
import com.example.actyourposeapp.databinding.FragmentExploreBinding
import com.example.actyourposeapp.screen.adapter.ExplorePhotoAdapter
import com.example.actyourposeapp.screen.adapter.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator


class ExploreFragment : Fragment() {


    private var _binding : FragmentExploreBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentExploreBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Tabs
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        binding?.viewPager?.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding?.tabs!!, binding?.viewPager!!) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        binding?.rvRecommendExplore?.adapter = ExplorePhotoAdapter()
        binding?.rvRecommendExplore?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2,
            R.string.tab_text_3,
            R.string.tab_text_4,
            R.string.tab_text_5,
        )

    }
}