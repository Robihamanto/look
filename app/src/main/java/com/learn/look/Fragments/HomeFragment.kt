package com.learn.look.Fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.learn.look.Adapter.MovieAdapter
import com.learn.look.Model.Movie

import com.learn.look.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.zip.Inflater


class HomeFragment : Fragment() {

    private lateinit var movieRecycleView : RecyclerView
    lateinit var movieAdapter: MovieAdapter

    private var homeViewModel: HomeViewModel = HomeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestMovies()
    }

    fun requestMovies() {
        homeViewModel.getMovies().observe(this, Observer {
            movieAdapter.movies = it!!
            movieAdapter.notifyDataSetChanged()
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view  = inflater.inflate(R.layout.fragment_home, container, false)
        movieRecycleView = view.findViewById(R.id.home_movie_list) as RecyclerView
        movieRecycleView.layoutManager = GridLayoutManager(context, 2)
        movieAdapter = MovieAdapter()
        movieRecycleView.adapter = movieAdapter
        return view
    }

}
