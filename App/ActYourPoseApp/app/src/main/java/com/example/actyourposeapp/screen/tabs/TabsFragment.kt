package com.example.actyourposeapp.screen.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.actyourposeapp.R
import com.example.actyourposeapp.databinding.FragmentTabsBinding
import com.example.actyourposeapp.screen.adapter.TabsPhotoAdapter


class TabsFragment : Fragment() {

    private var _binding : FragmentTabsBinding? = null
    private val binding get() = _binding

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
        val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)
        binding?.textView9?.text = getString(R.string.content_tab_text , index)

        binding?.rvPhotoList?.adapter = TabsPhotoAdapter()
        binding?.rvPhotoList?.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
    }

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
    }
}