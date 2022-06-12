package com.example.actyourposeapp.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.actyourposeapp.databinding.ItemExploreImageBinding
import com.example.actyourposeapp.databinding.ItemExploreImageTabBinding

class TabsPhotoAdapter(private val tabsList: ArrayList<String>): RecyclerView.Adapter<TabsPhotoAdapter.ListViewHolder>() {

    private lateinit var binding: ItemExploreImageTabBinding

    class ListViewHolder(var binding: ItemExploreImageTabBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        binding = ItemExploreImageTabBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val imageUrl = tabsList[position]
        Glide.with(holder.itemView.context).load(imageUrl).into(holder.binding.ivPhotoImage)

    }

    override fun getItemCount(): Int = tabsList.size
}