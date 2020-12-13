package com.example.picture_switcher.data


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    var results: List<Result> = listOf(),
    var total: Int = 0,
    @SerializedName("total_pages")
    var totalPages: Int = 0
) {
    data class Result(
        @SerializedName("alt_description")
        var altDescription: String = "",
        @SerializedName("blur_hash")
        var blurHash: String = "",
        var categories: List<Any> = listOf(),
        var color: String = "",
        @SerializedName("created_at")
        var createdAt: String = "",
        @SerializedName("current_user_collections")
        var currentUserCollections: List<Any> = listOf(),
        var description: Any = Any(),
        var height: Int = 0,
        var id: String = "",
        @SerializedName("liked_by_user")
        var likedByUser: Boolean = false,
        var likes: Int = 0,
        var links: Links = Links(),
        @SerializedName("promoted_at")
        var promotedAt: String = "",
        var sponsorship: Any = Any(),
        var tags: List<Tag> = listOf(),
        @SerializedName("updated_at")
        var updatedAt: String = "",
        var urls: Urls = Urls(),
        var user: User = User(),
        var width: Int = 0
    ) {
        data class Links(
            var download: String = "",
            @SerializedName("download_location")
            var downloadLocation: String = "",
            var html: String = "",
            var self: String = ""
        )

        data class Tag(
            var source: Source = Source(),
            var title: String = "",
            var type: String = ""
        ) {
            data class Source(
                var ancestry: Ancestry = Ancestry(),
                @SerializedName("cover_photo")
                var coverPhoto: CoverPhoto = CoverPhoto(),
                var description: String = "",
                @SerializedName("meta_description")
                var metaDescription: String = "",
                @SerializedName("meta_title")
                var metaTitle: String = "",
                var subtitle: String = "",
                var title: String = ""
            ) {
                data class Ancestry(
                    var category: Category = Category(),
                    var subcategory: Subcategory = Subcategory(),
                    var type: Type = Type()
                ) {
                    data class Category(
                        @SerializedName("pretty_slug")
                        var prettySlug: String = "",
                        var slug: String = ""
                    )

                    data class Subcategory(
                        @SerializedName("pretty_slug")
                        var prettySlug: String = "",
                        var slug: String = ""
                    )

                    data class Type(
                        @SerializedName("pretty_slug")
                        var prettySlug: String = "",
                        var slug: String = ""
                    )
                }

                data class CoverPhoto(
                    @SerializedName("alt_description")
                    var altDescription: String = "",
                    @SerializedName("blur_hash")
                    var blurHash: String = "",
                    var categories: List<Any> = listOf(),
                    var color: String = "",
                    @SerializedName("created_at")
                    var createdAt: String = "",
                    @SerializedName("current_user_collections")
                    var currentUserCollections: List<Any> = listOf(),
                    var description: String = "",
                    var height: Int = 0,
                    var id: String = "",
                    @SerializedName("liked_by_user")
                    var likedByUser: Boolean = false,
                    var likes: Int = 0,
                    var links: Links = Links(),
                    @SerializedName("promoted_at")
                    var promotedAt: String = "",
                    var sponsorship: Any = Any(),
                    @SerializedName("updated_at")
                    var updatedAt: String = "",
                    var urls: Urls = Urls(),
                    var user: User = User(),
                    var width: Int = 0
                ) {
                    data class Links(
                        var download: String = "",
                        @SerializedName("download_location")
                        var downloadLocation: String = "",
                        var html: String = "",
                        var self: String = ""
                    )

                    data class Urls(
                        var full: String = "",
                        var raw: String = "",
                        var regular: String = "",
                        var small: String = "",
                        var thumb: String = ""
                    )

                    data class User(
                        @SerializedName("accepted_tos")
                        var acceptedTos: Boolean = false,
                        var bio: Any = Any(),
                        @SerializedName("first_name")
                        var firstName: String = "",
                        var id: String = "",
                        @SerializedName("instagram_username")
                        var instagramUsername: String = "",
                        @SerializedName("last_name")
                        var lastName: String = "",
                        var links: Links = Links(),
                        var location: String = "",
                        var name: String = "",
                        @SerializedName("portfolio_url")
                        var portfolioUrl: String = "",
                        @SerializedName("profile_image")
                        var profileImage: ProfileImage = ProfileImage(),
                        @SerializedName("total_collections")
                        var totalCollections: Int = 0,
                        @SerializedName("total_likes")
                        var totalLikes: Int = 0,
                        @SerializedName("total_photos")
                        var totalPhotos: Int = 0,
                        @SerializedName("twitter_username")
                        var twitterUsername: Any = Any(),
                        @SerializedName("updated_at")
                        var updatedAt: String = "",
                        var username: String = ""
                    ) {
                        data class Links(
                            var followers: String = "",
                            var following: String = "",
                            var html: String = "",
                            var likes: String = "",
                            var photos: String = "",
                            var portfolio: String = "",
                            var self: String = ""
                        )

                        data class ProfileImage(
                            var large: String = "",
                            var medium: String = "",
                            var small: String = ""
                        )
                    }
                }
            }
        }

//        data class Urls(
//            var full: String = "",
//            var raw: String = "",
//            var regular: String = "",
//            var small: String = "",
//            var thumb: String = ""
//        )

        data class User(
            @SerializedName("accepted_tos")
            var acceptedTos: Boolean = false,
            var bio: String = "",
            @SerializedName("first_name")
            var firstName: String = "",
            var id: String = "",
            @SerializedName("instagram_username")
            var instagramUsername: String = "",
            @SerializedName("last_name")
            var lastName: String = "",
            var links: Links = Links(),
            var location: Any = Any(),
            var name: String = "",
            @SerializedName("portfolio_url")
            var portfolioUrl: String = "",
            @SerializedName("profile_image")
            var profileImage: ProfileImage = ProfileImage(),
            @SerializedName("total_collections")
            var totalCollections: Int = 0,
            @SerializedName("total_likes")
            var totalLikes: Int = 0,
            @SerializedName("total_photos")
            var totalPhotos: Int = 0,
            @SerializedName("twitter_username")
            var twitterUsername: String = "",
            @SerializedName("updated_at")
            var updatedAt: String = "",
            var username: String = ""
        ) {
            data class Links(
                var followers: String = "",
                var following: String = "",
                var html: String = "",
                var likes: String = "",
                var photos: String = "",
                var portfolio: String = "",
                var self: String = ""
            )

            data class ProfileImage(
                var large: String = "",
                var medium: String = "",
                var small: String = ""
            )
        }
    }
}