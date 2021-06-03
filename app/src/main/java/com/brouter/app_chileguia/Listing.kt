package com.brouter.app_chileguia

import com.google.gson.annotations.SerializedName

data class Listing(
    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("description") val description : String,
    @SerializedName("logo") val cover: String
    )
