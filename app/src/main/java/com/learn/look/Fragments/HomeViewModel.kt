package com.learn.look.Fragments

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.learn.look.Model.Movie
import com.learn.look.Model.MovieResponse
import com.learn.look.Model.Person
import com.learn.look.Model.PersonResponse
import com.learn.look.Network.RetrofitClient
import com.learn.look.Network.RetrofitInterface
import com.learn.look.Utils.API_KEY
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class HomeViewModel(): ViewModel() {

    val request = RetrofitClient.getRetrofitClient()?.create(RetrofitInterface::class.java)
    val peopleData: MutableLiveData<List<Person>> = MutableLiveData()
    val movieData: MutableLiveData<List<Movie>> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun getMovies(): LiveData<List<Movie>> {
        request?.getMovieList("popular", API_KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe { response ->
                if (response.isSuccessful) {
                    try {
                        val movieResponse = response?.body() as MovieResponse
                        movieData.value = movieResponse.movies
                        println("movies : " + movieResponse.movies)
                    } catch (e: Exception) {
                        e.stackTrace
                    }
                } else {
                    Log.d("profile-data", response?.body().toString())
                }
            }
        return movieData
    }

    @SuppressLint("CheckResult")
    fun getPeople() : LiveData<List<Person>> {
        request?.getPersonList("popular", API_KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe { response ->
                if (response.isSuccessful) {
                    try {
                        val personResponse = response?.body() as PersonResponse
                        peopleData.value = personResponse.people
                        println("people : " + personResponse.people)
                    } catch (e: Exception) {
                        print(e.stackTrace)
                    }
                }
            }
        return peopleData
    }
}