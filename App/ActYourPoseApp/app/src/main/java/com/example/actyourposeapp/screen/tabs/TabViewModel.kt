package com.example.actyourposeapp.screen.tabs

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.actyourposeapp.api.ApiConfig
import com.example.actyourposeapp.api.response.MsgTabsItem
import com.example.actyourposeapp.api.response.TabsSectionResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TabViewModel : ViewModel() {
    private val _tabs = MutableLiveData<List<MsgTabsItem>>()
    val tabs : MutableLiveData<List<MsgTabsItem>> = _tabs

    fun getPhotoCategory(category: String){
        val client = ApiConfig.getApiService().getTabsPhoto(category)
        client.enqueue(object : Callback<TabsSectionResponse> {
            override fun onResponse(
                call: Call<TabsSectionResponse>,
                response: Response<TabsSectionResponse>
            ) {
                if (response.isSuccessful) {
                    _tabs.value = response.body()?.msg
                } else {
                    Log.e(TAG, "error : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TabsSectionResponse>, t: Throwable) {
                Log.e(TAG, "onFailure : ${t.message}")
            }

        })
    }

    companion object {
        const val TAG = "CameraViewModel"
    }
}