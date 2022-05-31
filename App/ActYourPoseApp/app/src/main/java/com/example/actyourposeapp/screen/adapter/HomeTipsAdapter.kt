package com.example.actyourposeapp.screen.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.actyourposeapp.databinding.ItemPoseBinding
import com.example.actyourposeapp.databinding.TopicItemBinding




class HomeTipsAdapter : RecyclerView.Adapter<HomeTipsAdapter.ListViewHolder>() {

    private lateinit var binding: TopicItemBinding

    class ListViewHolder(var binding: TopicItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        binding = TopicItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val imageDummyUrl =
            "https://media.product.which.co.uk/prod/images/original/gm-8d3b15d7-35b1-465e-b75d-6f12ba4a071f-digital-cameraslead.jpeg"
        val textDummyTitle = "How To Take A photo"

        holder.binding.tvTopicTitle.text = textDummyTitle
        Glide.with(holder.itemView.context).load(imageDummyUrl).into(holder.binding.ivLearnTopic)
    }

    override fun getItemCount(): Int = 8
}