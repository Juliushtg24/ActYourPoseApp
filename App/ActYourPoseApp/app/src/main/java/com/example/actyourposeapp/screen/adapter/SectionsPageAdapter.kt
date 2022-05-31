package com.example.actyourposeapp.screen.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.actyourposeapp.screen.explore.ExploreFragment
import com.example.actyourposeapp.screen.home.HomeFragment
import com.example.actyourposeapp.screen.tabs.TabsFragment

class SectionsPagerAdapter(activity: ExploreFragment) : FragmentStateAdapter(activity) {

    private var userData : String = ""

    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = TabsFragment()
        fragment.arguments = Bundle().apply {
            putInt(TabsFragment.ARG_SECTION_NUMBER, position+1)
        }
        return fragment
    }
}