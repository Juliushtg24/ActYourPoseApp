package com.example.actyourposeapp.api.response

import com.google.gson.annotations.SerializedName

data class RecommendResponse(

	@field:SerializedName("photoUrl")
	val photoUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String
)
