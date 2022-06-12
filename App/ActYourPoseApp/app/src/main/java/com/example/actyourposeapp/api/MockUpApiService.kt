package com.example.actyourposeapp.api

import com.example.actyourposeapp.api.response.PoseResponseItem
import com.example.actyourposeapp.api.response.RecommendResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MockUpApiService {

    @GET("beginner")
    fun getBeginnerPose() : Call<List<PoseResponseItem>>

    @GET("{mode}")
    fun getPoseMode(
        @Path("mode") mode : String
    ) : Call<List<PoseResponseItem>>

    @GET("recommend")
    fun getRecommendSection() : Call<List<RecommendResponse>>
}