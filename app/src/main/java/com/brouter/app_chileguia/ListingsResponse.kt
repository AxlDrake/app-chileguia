package com.brouter.app_chileguia

import com.google.gson.annotations.SerializedName

data class ListingsResponse(
    @SerializedName(value = "status") var status:String,
    @SerializedName( value = "message") var result : List<Listing>
    )
