package com.example.actyourposeapp.api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Category(
    val imageUrl: String,
    val title: String
) : Parcelable