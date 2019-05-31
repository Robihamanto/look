package com.learn.look.Model

import com.google.gson.annotations.SerializedName

data class MovieResponse (

    @SerializedName("results")
    var movies: List<Movie>? = null
)

data class Movie(

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("poster_path")
    var posterPath: String? = null,

    @SerializedName("production_companies")
    var productionCompanies: List<Company>? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("original_title")
    var originalTitle: String? = null,

    @SerializedName("backdrop_path")
    var backdropPath: String? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("release_date")
    var releaseDate: String? = null,

    @SerializedName("production_countries")
    var productionCountry: List<ProductionCountry>? = null,

    @SerializedName("runtime")
    var runtime: Int? = null,

    @SerializedName("vote_average")
    var voteAverage: Double? = null,

    @SerializedName("popularity")
    var popularity: Double? = null
)

data class ProductionCountry (

    @SerializedName("iso_3166_1")
    var iso_3166_1: String? = null,

    @SerializedName("name")
    var name: String? = null
)

data class Company (

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("logo_path")
    var logoPath: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("origin_country")
    var originCountry: String? = null


)