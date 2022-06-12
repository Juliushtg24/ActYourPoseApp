package com.example.actyourposeapp.api.response

import com.google.gson.annotations.SerializedName

data class TabsSectionResponse(

	@field:SerializedName("msg")
	val msg: List<MsgTabsItem>,

	@field:SerializedName("status")
	val status: String
)

data class MsgTabsItem(

	@field:SerializedName("photoUrl")
	val photoUrl: String
)
