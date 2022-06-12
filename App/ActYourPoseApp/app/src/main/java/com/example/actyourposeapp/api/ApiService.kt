package com.example.actyourposeapp.api

import com.example.actyourposeapp.api.response.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("api/getAll")
    fun getTopics() : Call<TopicResponse>


    @GET("api/{tabs}")
    fun getTabsPhoto(
        @Path("tabs") tabs : String
    ) : Call<TabsSectionResponse>

    @GET("api/{mode}")
    fun getPredictPose(
        @Path("mode") mode : String
    ) : Call<PoseItemResponse>

    @Multipart
    @POST("predict")
    fun predictRoom(
        @Part file: MultipartBody.Part,
    ) : Call<FileResponse>


}