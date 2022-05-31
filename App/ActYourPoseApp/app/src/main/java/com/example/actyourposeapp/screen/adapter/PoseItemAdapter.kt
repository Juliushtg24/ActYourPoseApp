package com.example.actyourposeapp.screen.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.actyourposeapp.databinding.ItemPoseBinding

class PoseItemAdapter : RecyclerView.Adapter<PoseItemAdapter.ListViewHolder>(){

        private lateinit var binding: ItemPoseBinding

        class ListViewHolder(var binding: ItemPoseBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ListViewHolder {
            binding = ItemPoseBinding.inflate(LayoutInflater.from(parent.context), parent , false)
            return ListViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
            holder.binding.fabPose.setOnClickListener {
                Log.d("PoseAdapter" , "Button Pose Clicked")
            }
        }

        override fun getItemCount(): Int = 8
}