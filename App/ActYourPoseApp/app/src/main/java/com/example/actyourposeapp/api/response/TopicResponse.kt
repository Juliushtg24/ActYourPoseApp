package com.example.actyourposeapp.api.response

import com.google.gson.annotations.SerializedName

data class TopicResponse(

	@field:SerializedName("msg")
	val msg: List<MsgItem>,

	@field:SerializedName("status")
	val status: String
)

data class MsgItem(

	@field:SerializedName("photoUrl")
	val photoUrl: String,

	@field:SerializedName("refrence")
	val refrence: String,

	@field:SerializedName("description2")
	val description2: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("description3")
	val description3: String,

	@field:SerializedName("description1")
	val description1: String
)
