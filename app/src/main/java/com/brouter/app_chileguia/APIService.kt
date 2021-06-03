package com.brouter.app_chileguia

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getAllListings(@Url url:String ): Response<ListingsResponse>
}