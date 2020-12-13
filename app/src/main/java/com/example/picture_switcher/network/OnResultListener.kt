package com.example.picture_switcher.network

import com.example.picture_switcher.data.UnsplashPhoto

interface OnResultListener {

    fun onSuccess(photoListResponse: MutableList<UnsplashPhoto>)

    fun onEmptySearch()

    fun onError(message: String?)
}