package com.example.actyourposeapp.screen.camera

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.actyourposeapp.api.MockUpApiConfig
import com.example.actyourposeapp.api.response.PoseResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CameraViewModel : ViewModel() {
    private val _pose = MutableLiveData<List<PoseResponseItem>>()
    val pose : MutableLiveData<List<PoseResponseItem>> = _pose


    fun getPose() {
        val client = MockUpApiConfig.getApiService().getBeginnerPose()
        client.enqueue(object : Callback<List<PoseResponseItem>> {
            override fun onResponse(
                call: Call<List<PoseResponseItem>>,
                response: Response<List<PoseResponseItem>>
            ) {
                if (response.isSuccessful) {
                    _pose.value = response.body()
                } else {
                    Log.e(TAG, "error : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<PoseResponseItem>>, t: Throwable) {
                Log.e(TAG, "onFailure : ${t.message}")
            }

        })
    }

    fun getPoseMode(mode: String){
        val client = MockUpApiConfig.getApiService().getPoseMode(mode)
        client.enqueue(object : Callback<List<PoseResponseItem>> {
            override fun onResponse(
                call: Call<List<PoseResponseItem>>,
                response: Response<List<PoseResponseItem>>
            ) {
                if (response.isSuccessful) {
                    _pose.value = response.body()
                } else {
                    Log.e(TAG, "error : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<PoseResponseItem>>, t: Throwable) {
                Log.e(TAG, "onFailure : ${t.message}")
            }

        })
    }

    companion object {
        const val TAG = "CameraViewModel"
    }
}