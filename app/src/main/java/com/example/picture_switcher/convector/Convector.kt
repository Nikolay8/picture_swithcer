package com.example.picture_switcher.convector

import com.example.picture_switcher.data.SearchResponse
import com.example.picture_switcher.data.UnsplashPhoto

class Convector {
    fun searchResponseToUnsplashPhoto(searchResponse: SearchResponse): MutableList<UnsplashPhoto> {
        val convertedList = mutableListOf<UnsplashPhoto>()
        for (result in searchResponse.results) {
            val unsplashPhoto = UnsplashPhoto()
            unsplashPhoto.urls = result.urls
            unsplashPhoto.links.download = result.links.download
            convertedList.add(unsplashPhoto)
        }
        return convertedList
    }
}