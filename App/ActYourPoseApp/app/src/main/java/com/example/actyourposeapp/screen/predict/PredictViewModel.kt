package com.example.actyourposeapp.screen.predict

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.actyourposeapp.api.ApiConfig
import com.example.actyourposeapp.api.response.PoseItem
import com.example.actyourposeapp.api.response.PoseItemResponse
import com.example.actyourposeapp.api.response.PoseResponseItem
import com.example.actyourposeapp.screen.camera.CameraViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PredictViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _pose = MutableLiveData<List<PoseItem>>()
    val pose : MutableLiveData<List<PoseItem>> = _pose


    fun getPoseMode(mode: String){
        val client = ApiConfig.getApiService().getPredictPose(mode)
        client.enqueue(object : Callback<PoseItemResponse> {
            override fun onResponse(
                call: Call<PoseItemResponse>,
                response: Response<PoseItemResponse>
            ) {
                if (response.isSuccessful) {
                    _pose.value = response.body()?.msg
                } else {
                    Log.e(CameraViewModel.TAG, "error : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PoseItemResponse>, t: Throwable) {
                Log.e(CameraViewModel.TAG, "onFailure : ${t.message}")
            }

        })
    }

    companion object {
        const val TAG = "PredictViewModel"
    }


}