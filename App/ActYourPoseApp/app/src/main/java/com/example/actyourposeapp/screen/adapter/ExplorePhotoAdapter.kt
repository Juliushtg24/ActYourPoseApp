package com.example.actyourposeapp.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.actyourposeapp.databinding.ItemExploreImageBinding
import com.example.actyourposeapp.databinding.TopicItemBinding

class ExplorePhotoAdapter : RecyclerView.Adapter<ExplorePhotoAdapter.ListViewHolder>() {

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
        val imageDummyUrl =
            "https://media.istockphoto.com/photos/aerial-view-of-tokyo-cityscape-with-fuji-mountain-in-japan-picture-id1131743616?b=1&k=20&m=1131743616&s=170667a&w=0&h=stgpBO1SxauKdLh-9dcCecTZiGdkPapISKRs9oxuwFU="
        val textDummyTitle = "City A"

        holder.binding.tvRecommendPhoto.text = textDummyTitle
        Glide.with(holder.itemView.context).load(imageDummyUrl).into(holder.binding.ivPhotoImage)
    }

    override fun getItemCount(): Int = 8
}
