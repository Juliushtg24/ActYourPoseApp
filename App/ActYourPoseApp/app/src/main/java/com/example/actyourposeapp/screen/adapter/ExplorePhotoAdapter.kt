package com.example.actyourposeapp.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.actyourposeapp.api.Category
import com.example.actyourposeapp.databinding.ItemExploreImageBinding

class ExplorePhotoAdapter(private val itemList: ArrayList<Category>) : RecyclerView.Adapter<ExplorePhotoAdapter.ListViewHolder>() {

    private lateinit var binding: ItemExploreImageBinding

    class ListViewHolder(var binding: ItemExploreImageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        binding = ItemExploreImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (imageUrl , title) = itemList[position]

        holder.binding.tvRecommendPhoto.text = title
        Glide.with(holder.itemView.context).load(imageUrl).into(holder.binding.ivPhotoImage)
    }

    override fun getItemCount(): Int = itemList.size
}
