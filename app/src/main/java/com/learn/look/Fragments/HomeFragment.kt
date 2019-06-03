package com.learn.look.Fragments

import android.os.Bundle
import android.arch.lifecycle.Observer
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.shimmer.ShimmerFrameLayout
import com.learn.look.Adapter.HomeAdapter
import com.learn.look.Adapter.MovieBackDropAdapter

import com.learn.look.R
import kotlinx.android.synthetic.main.backdrop_movie_placeholder_layout.view.*
import kotlinx.android.synthetic.main.home_backdrop_list.view.*
import kotlinx.android.synthetic.main.home_people_list.view.*


class HomeFragment : Fragment() {

    private lateinit var homeRecycleView : RecyclerView
    private lateinit var homeAdapter: HomeAdapter

    private var homeViewModel: HomeViewModel = HomeViewModel()
    private lateinit var movieBackdropShimmerViewContainer: ShimmerFrameLayout
    private lateinit var peopleShimmerViewContainer: ShimmerFrameLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view  = inflater.inflate(R.layout.fragment_home, container, false)
        homeRecycleView = view.findViewById(R.id.home_list) as RecyclerView
        homeRecycleView.layoutManager = LinearLayoutManager(context)
        homeAdapter = HomeAdapter()
        homeRecycleView.adapter = homeAdapter

        val shimmerViewPeople = inflater.inflate(R.layout.home_people_list, container, false)
        peopleShimmerViewContainer = shimmerViewPeople.shimmer_people_view_container

        val shimmerViewBackdrop  = inflater.inflate(R.layout.home_backdrop_list, container, false)
        movieBackdropShimmerViewContainer = shimmerViewBackdrop.shimmer_backdrop_view_container
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchData()
    }

    override fun onResume() {
        super.onResume()
        peopleShimmerViewContainer.startShimmer()
        movieBackdropShimmerViewContainer.startShimmer()
    }

    override fun onPause() {
        super.onPause()
        peopleShimmerViewContainer.stopShimmer()
        movieBackdropShimmerViewContainer.stopShimmer()
    }

    private fun fetchData() {

        homeViewModel.getPeople().observe(this, Observer {
            homeAdapter.people = it!!
            homeAdapter.notifyDataSetChanged()
            peopleShimmerViewContainer.stopShimmer()
            peopleShimmerViewContainer.visibility = View.GONE
        })

        homeViewModel.getMovies().observe(this, Observer {
            homeAdapter.movies = it!!.reversed()
            homeAdapter.notifyDataSetChanged()
            movieBackdropShimmerViewContainer.stopShimmer()
            movieBackdropShimmerViewContainer.visibility = View.GONE
        })


    }

}
