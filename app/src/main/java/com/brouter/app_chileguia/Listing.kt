package com.brouter.app_chileguia

import com.google.gson.annotations.SerializedName

data class Listing(
    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("description") val description : String,
    @SerializedName("logo") val cover: String?,
    @SerializedName("email") val email: String? = "",
    @SerializedName("contact_person") val contactPerson : String? = "",
    @SerializedName("phone_number") val phoneNumber : String? = "",
    @SerializedName("mobile_number") val mobileNumber : String? = ""
    )
