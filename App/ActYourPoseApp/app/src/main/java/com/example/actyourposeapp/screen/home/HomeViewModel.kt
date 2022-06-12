package com.example.actyourposeapp.screen.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.actyourposeapp.api.ApiConfig
import com.example.actyourposeapp.api.response.MsgItem
import com.example.actyourposeapp.api.response.TopicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val _topic = MutableLiveData<List<MsgItem>>()
    val topic : LiveData<List<MsgItem>> = _topic


    fun getTopicLearn() {
        val client = ApiConfig.getApiService().getTopics()
        client.enqueue(object : Callback<TopicResponse> {
            override fun onResponse(
                call: Call<TopicResponse>,
                response: Response<TopicResponse>
            ) {
                if (response.isSuccessful) {
                    _topic.value = response.body()?.msg
                } else {
                    Log.e(TAG, "error : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TopicResponse>, t: Throwable) {
                Log.e(TAG, "onFailure : ${t.message}")
            }

        })
    }

    companion object {
        const val TAG = "HomeViewModel"
    }
}