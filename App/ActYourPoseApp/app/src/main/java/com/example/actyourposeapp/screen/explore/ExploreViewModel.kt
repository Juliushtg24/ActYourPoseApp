package com.example.actyourposeapp.screen.explore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.actyourposeapp.api.MockUpApiConfig
import com.example.actyourposeapp.api.response.RecommendResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExploreViewModel : ViewModel() {

    private val _item = MutableLiveData<List<RecommendResponse>>()
    val item : LiveData<List<RecommendResponse>> = _item


    fun getSectionCategory() {
        val client = MockUpApiConfig.getApiService().getRecommendSection()
        client.enqueue(object : Callback<List<RecommendResponse>> {
            override fun onResponse(
                call: Call<List<RecommendResponse>>,
                response: Response<List<RecommendResponse>>
            ) {
                if (response.isSuccessful) {
                    _item.value = response.body()
                } else {
                    Log.e(TAG, "error : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<RecommendResponse>>, t: Throwable) {
                Log.e(TAG, "onFailure : ${t.message}")
            }

        })
    }

    companion object {
        const val TAG = "HomeViewModel"
    }
}