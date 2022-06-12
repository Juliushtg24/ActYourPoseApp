package com.example.actyourposeapp.api.response

import com.google.gson.annotations.SerializedName

data class PoseResponse(

	@field:SerializedName("PoseResponse")
	val poseResponse: List<PoseResponseItem>
)

data class PoseResponseItem(

	@field:SerializedName("photoUrl")
	val photoUrl: String,

	@field:SerializedName("id")
	val id: String
)
