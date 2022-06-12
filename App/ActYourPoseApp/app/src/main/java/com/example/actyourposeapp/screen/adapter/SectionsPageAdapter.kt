package com.example.actyourposeapp.screen.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.actyourposeapp.screen.explore.ExploreFragment
import com.example.actyourposeapp.screen.tabs.TabsFragment

class SectionsPagerAdapter(activity: ExploreFragment) : FragmentStateAdapter(activity) {


    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = TabsFragment()
        var tabsCategory: String? = null
        when (position) {
            0 -> tabsCategory = "getBandung"
            1 -> tabsCategory = "getBali"
            2 -> tabsCategory = "getPapua"
        }
        fragment.arguments = Bundle().apply {
            putInt(TabsFragment.ARG_SECTION_NUMBER, position+1)
            putString(TabsFragment.ARG_SECTION_TITLE, tabsCategory)
        }
        return fragment
    }
}