package com.example.splashscreenlastproject.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article (
    @SerializedName("id")
    val id : String?,

    @SerializedName("title")
    val title : String?,

    @SerializedName("creator")
    val creator : String?,

    @SerializedName("description")
    val description : String?,

    @SerializedName("image")
    val image : String?,

    ) : Parcelable {
    constructor() : this("", "", "", "", "")
}
