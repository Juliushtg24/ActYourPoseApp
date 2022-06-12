package com.example.actyourposeapp.screen.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.actyourposeapp.api.Topic
import com.example.actyourposeapp.databinding.TopicItemBinding
import com.example.actyourposeapp.screen.detail.DetailActivity


class HomeTipsAdapter(private val listTopicLearn: ArrayList<Topic>) :
    RecyclerView.Adapter<HomeTipsAdapter.ListViewHolder>() {

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
        val (title, photoUrl, reference, desc1, desc2, desc3) = listTopicLearn[position]

        holder.binding.tvTopicTitle.text = title
        Glide.with(holder.itemView.context).load(photoUrl).into(holder.binding.ivLearnTopic)

        holder.itemView.setOnClickListener {

            val topic = Topic(
                title,
                photoUrl,
                reference,
                desc1,
                desc2,
                desc3
            )

            val intentDetail = Intent(it.context, DetailActivity::class.java)
            intentDetail.putExtra("extra_topic", topic)
            it.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int = listTopicLearn.size
}