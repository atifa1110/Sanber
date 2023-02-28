package com.atifa.sanber

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("top-headlines")
    fun getNews(
        @Query("country") country: String,
        @Query("pageSize") pageSize : Int,
        @Query("apiKey") apiKey : String
    ): Call<ResponseNews>

    @GET("top-headlines")
    fun getCategoryNews(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("pageSize") pageSize : Int,
        @Query("apiKey") apiKey : String
    ): Call<ResponseNews>
}