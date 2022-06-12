package com.example.actyourposeapp.api.response

import com.google.gson.annotations.SerializedName

data class PoseItemResponse(

	@field:SerializedName("msg")
	val msg: List<PoseItem>,

	@field:SerializedName("status")
	val status: String
)

data class PoseItem(

	@field:SerializedName("photourl")
	val photourl: String
)
