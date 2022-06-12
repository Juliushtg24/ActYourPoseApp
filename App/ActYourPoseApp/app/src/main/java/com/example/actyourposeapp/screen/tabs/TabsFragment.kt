package com.example.actyourposeapp.screen.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.actyourposeapp.R
import com.example.actyourposeapp.api.response.MsgTabsItem
import com.example.actyourposeapp.databinding.FragmentTabsBinding
import com.example.actyourposeapp.screen.adapter.TabsPhotoAdapter


class TabsFragment : Fragment() {

    private var _binding : FragmentTabsBinding? = null
    private val binding get() = _binding

    private val tabsViewModel by viewModels<TabViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTabsBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val apiTitle = arguments?.getString(ARG_SECTION_TITLE, "getBandung")


        tabsViewModel.tabs.observe(viewLifecycleOwner) {
            setPhotoApi(it)
        }

        if (apiTitle != null) {
            tabsViewModel.getPhotoCategory(apiTitle)
        }
    }


    private fun setPhotoApi(photos: List<MsgTabsItem>){
        val listPhoto = ArrayList<String>()
        for(photo in photos){
            val url = photo.photoUrl
            listPhoto.add(url)
        }

        binding?.rvPhotoList?.adapter = TabsPhotoAdapter(listPhoto)
        binding?.rvPhotoList?.layoutManager = GridLayoutManager(activity, 3)
    }

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
        const val ARG_SECTION_TITLE = "section_title"
    }
}