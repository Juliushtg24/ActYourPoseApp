package com.example.actyourposeapp.api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Topic(
    val title: String,
    val photoUrl: String,
    val reference: String,
    val desc1: String,
    val desc2: String,
    val desc3: String
) : Parcelable
