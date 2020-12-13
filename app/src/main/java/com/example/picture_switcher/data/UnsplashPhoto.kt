package com.example.picture_switcher.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UnsplashPhoto(
    val color: String = "",
    @SerializedName("created_at")
    val createdAt: String = "",
    val downloads: Int = 0,
    val exif: Exif = Exif(),
    val height: Int = 0,
    val id: String = "",
    val likes: Int = 0,
    val links: Links = Links(),
    val location: Location = Location(),
    @SerializedName("updated_at")
    val updatedAt: String = "",
    var urls: Urls = Urls(),
    val user: User = User(),
    val views: Int = 0,
    val width: Int = 0
) : Parcelable {
    @Parcelize
    data class Exif(
        val aperture: String = "",
        @SerializedName("exposure_time")
        val exposureTime: String = "",
        @SerializedName("focal_length")
        val focalLength: String = "",
        val iso: Int = 0,
        val make: String = "",
        val model: String = ""
    ) : Parcelable

    @Parcelize
    data class Links(
        var download: String = "",
        @SerializedName("download_location")
        val downloadLocation: String = "",
        val html: String = "",
        val self: String = ""
    ) : Parcelable

    @Parcelize
    data class Location(
        val city: String = "",
        val country: String = "",
        val name: String = "",
        val position: Position = Position()
    ) : Parcelable {
        @Parcelize
        data class Position(
            val latitude: Double = 0.0,
            val longitude: Double = 0.0
        ) : Parcelable
    }

    //    @Parcelize
//    data class Urls(
//        val full: String = "",
//        val raw: String = "",
//        val regular: String = "",
//        val small: String = "",
//        val thumb: String = ""
//    ):Parcelable
    @Parcelize
    data class User(
        val bio: String = "",
        @SerializedName("first_name")
        val firstName: String = "",
        val id: String = "",
        @SerializedName("instagram_username")
        val instagramUsername: String = "",
        @SerializedName("last_name")
        val lastName: String = "",
        val links: Links = Links(),
        val location: String = "",
        val name: String = "",
        @SerializedName("portfolio_url")
        val portfolioUrl: String = "",
        @SerializedName("profile_image")
        val profileImage: ProfileImage = ProfileImage(),
        @SerializedName("total_collections")
        val totalCollections: Int = 0,
        @SerializedName("total_likes")
        val totalLikes: Int = 0,
        @SerializedName("total_photos")
        val totalPhotos: Int = 0,
        @SerializedName("twitter_username")
        val twitterUsername: String = "",
        @SerializedName("updated_at")
        val updatedAt: String = "",
        val username: String = ""
    ) : Parcelable {
        @Parcelize
        data class Links(
            val followers: String = "",
            val following: String = "",
            val html: String = "",
            val likes: String = "",
            val photos: String = "",
            val portfolio: String = "",
            val self: String = ""
        ) : Parcelable

        @Parcelize
        data class ProfileImage(
            val large: String = "",
            val medium: String = "",
            val small: String = ""
        ) : Parcelable
    }
}
