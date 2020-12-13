package com.example.picture_switcher.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Urls(
    var full: String = "",
    var raw: String = "",
    var regular: String = "",
    var small: String = "",
    var thumb: String = ""
) : Parcelable
