package com.example.picture_switcher.network

import com.example.picture_switcher.constant.Const
import com.example.picture_switcher.convector.Convector
import com.example.picture_switcher.data.SearchResponse
import com.example.picture_switcher.data.UnsplashPhoto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiClient {

    private var mService = Common.retrofitService
    private val convector = Convector()

    fun getRandomPhoto(onResultListener: OnResultListener, page: Int) {
        mService.getRandomPhoto(Const().ACCESS_KEY.toString(), page)
            .enqueue(object : Callback<MutableList<UnsplashPhoto>> {
                override fun onResponse(
                    call: Call<MutableList<UnsplashPhoto>>,
                    response: Response<MutableList<UnsplashPhoto>>
                ) {
                    try {
                        onResultListener.onSuccess(response.body() as MutableList<UnsplashPhoto>)
                    } catch (exception: Exception) {
                        onResultListener.onError(exception.message)
                    }
                }

                override fun onFailure(call: Call<MutableList<UnsplashPhoto>>, t: Throwable) {
                    onResultListener.onError(t.message)
                }
            })
    }

    fun searchPhoto(searchRequest: String, onResultListener: OnResultListener, page: Int) {
        mService.searchPhoto(Const().ACCESS_KEY.toString(), searchRequest, page).enqueue(
            object : Callback<SearchResponse> {
                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>
                ) {
                    try {
                        val searchResponse = response.body() as SearchResponse
                        if (searchResponse.results.isNotEmpty()) {
                            onResultListener.onSuccess(
                                convector.searchResponseToUnsplashPhoto(
                                    searchResponse
                                )
                            )
                        } else {
                            onResultListener.onEmptySearch()
                        }

                    } catch (ex: NullPointerException) {
                        onResultListener.onEmptySearch()
                    }
                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    onResultListener.onError(t.message)
                }
            })
    }
}