package com.example.actyourposeapp.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.actyourposeapp.databinding.ItemExploreImageBinding
import com.example.actyourposeapp.databinding.ItemExploreImageTabBinding

class TabsPhotoAdapter: RecyclerView.Adapter<TabsPhotoAdapter.ListViewHolder>() {

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
        val imageDummyUrl =
            "https://i.pinimg.com/736x/c8/7d/e9/c87de96bc79d9906106367cf1c0649f4.jpg"

        Glide.with(holder.itemView.context).load(imageDummyUrl).into(holder.binding.ivPhotoImage)
    }

    override fun getItemCount(): Int = 8
}