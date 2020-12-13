package com.example.picture_switcher.network

import com.example.picture_switcher.data.SearchResponse
import com.example.picture_switcher.data.UnsplashPhoto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {
    @GET("photos")
    fun getRandomPhoto(
        @Query("client_id") id: String,
        @Query("page") page: Int
    ): Call<MutableList<UnsplashPhoto>>

    @GET("search/photos")
    fun searchPhoto(
        @Query("client_id") id: String,
        @Query("query") request: String,
        @Query("page") page: Int
    ): Call<SearchResponse>
}