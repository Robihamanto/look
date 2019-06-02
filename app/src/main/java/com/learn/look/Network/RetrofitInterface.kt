package com.learn.look.Network

import com.learn.look.Model.Movie
import com.learn.look.Model.MovieResponse
import com.learn.look.Model.PersonResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("movie/{category}")
    fun getMovieList(@Path("category") category: String,
                     @Query("api_key") apiKey: String): Single<Response<MovieResponse>>

    @GET("person/{category}")
    fun getPersonList(@Path("category") category: String,
                     @Query("api_key") apiKey: String): Single<Response<PersonResponse>>


    @GET("movie/{movieId}")///movie/420817?
    fun getMovieDetail(@Path("movieId") id: Int,
                       @Query("api_key") apiKey: String
                        ): Single<Response<Movie>>



}