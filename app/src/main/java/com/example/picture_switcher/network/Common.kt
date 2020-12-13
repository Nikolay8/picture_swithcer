package com.example.picture_switcher.network

object Common {
    private const val BASE_URL = "https://api.unsplash.com/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}