package com.example.splashscreenlastproject.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleResponse (
    @SerializedName("results")
    val articles : List<Article>

) : Parcelable {
    constructor() : this(mutableListOf())
}