package com.example.actyourposeapp.screen.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.actyourposeapp.api.Topic
import com.example.actyourposeapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setTopic()

        binding.ivBackButton.setOnClickListener {
            finish()
        }

    }


    private fun setTopic(){
        val topic = intent.getParcelableExtra<Topic>(EXTRA_TOPIC)

        Glide.with(this).load(topic?.photoUrl).into(binding.ivTopicItem)

        binding.apply {
            tvTitleDetail.text = topic?.title
            tvDesc1.text = topic?.desc1
            tvDesc2.text = topic?.desc2
            tvDesc3.text = topic?.desc3
        }
    }

    companion object {
        const val EXTRA_TOPIC = "extra_topic"
    }
}