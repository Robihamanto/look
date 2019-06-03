package com.learn.look.Model

import com.google.gson.annotations.SerializedName

data class MovieReviewResponse (

    @SerializedName("results")
    var movies: List<Movie>? = null
)

data class Review (

    @SerializedName("author")
    var author: String? = null,

    @SerializedName("content")
    var content: String? = null,

    @SerializedName("id")
    var id: String? = null,

    @SerializedName("url")
    var url: String? = null
)