package com.learn.look.Adapter

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.learn.look.Model.Movie
import com.learn.look.Model.MovieResponse
import com.learn.look.Network.RetrofitClient
import com.learn.look.Network.RetrofitInterface
import com.learn.look.Utils.API_KEY
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class MovieDetailViewModel : ViewModel() {

    val request = RetrofitClient.getRetrofitClient()?.create(RetrofitInterface::class.java)
    val movieData: MutableLiveData<Movie> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun getMovie(id: Int): LiveData<Movie> {
        request?.getMovieDetail(id, API_KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe { response ->
                if (response.isSuccessful) {
                    try {
                        val movieResponse = response?.body() as Movie
                        movieData.value = movieResponse
                    } catch (e: Exception) {
                        e.stackTrace
                    }
                } else {
                    Log.d("profile-data", response?.body().toString())
                }
            }
        return movieData
    }

}