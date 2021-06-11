package com.brouter.app_chileguia

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getAllListings(@Url url:String ): Response<ListingsResponse>

    @POST("/listings/search")
    suspend fun  getSearchListings(@Body requestBody: RequestBody): Response<ListingsResponse>

    @GET
    suspend fun getDetailListing(@Url url:String): Response<ListingsResponse>
}