package com.learn.look.Fragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.learn.look.Adapter.MovieAdapter

import com.learn.look.R


class TopRatedMovieFragment : Fragment() {

    private lateinit var movieRecycleView : RecyclerView
    lateinit var movieAdapter: MovieAdapter

    private var topRatedMovieViewModel: TopRatedMovieViewModel = TopRatedMovieViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestMovies()
    }

    fun requestMovies() {
        topRatedMovieViewModel.getMovies().observe(this, Observer {
            movieAdapter.movies = it!!
            movieAdapter.notifyDataSetChanged()
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view  = inflater.inflate(R.layout.fragment_top_rated, container, false)
        movieRecycleView = view.findViewById(R.id.top_rated_movie_list) as RecyclerView
        movieRecycleView.layoutManager = GridLayoutManager(context, 2)
        movieAdapter = MovieAdapter()
        movieRecycleView.adapter = movieAdapter
        return view
    }

}
