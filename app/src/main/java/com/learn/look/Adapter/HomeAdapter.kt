package com.learn.look.Adapter

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout
import com.learn.look.Model.Company
import com.learn.look.Model.Movie
import com.learn.look.Model.Person
import com.learn.look.R
import com.learn.look.Utils.IMAGE_BASE_URL
import com.learn.look.View.MovieDetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.home_backdrop_list.view.*
import kotlinx.android.synthetic.main.home_people_list.view.*
import kotlinx.android.synthetic.main.recycler_list_title_header.view.*
import kotlinx.android.synthetic.main.recycler_movie_movie.view.*
import kotlinx.android.synthetic.main.reycler_list_company.view.*

class HomeAdapter(var people: List<Person>? = null, var companies: List<Company>? = null, var movies: List<Movie>? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var peopleAdapter: PeopleAdapter
    private lateinit var movieBackDropAdapter: MovieBackDropAdapter

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (position) {
            1 -> {
                val view = holder as PersonViewHolder
                view.bindView()
            }
            2 -> {
                val view = holder as BackdropViewHolder
                view.bindView()
            }
            0 -> {
                val view = holder as TitleHeaderViewHolder
                view.bindView("Popular Actors")
            }
            3 -> {
                val view = holder as TitleHeaderViewHolder
                view.bindView("Latest Movie")
            }
            else -> {
                val view = holder as MovieViewHolder
                val movie = movies?.get(position - 4)
                view.movie = movie
                view.bindView(movie)
            }
        }
    }

    override fun getItemCount(): Int {
        return movies?.size ?: 2 + 4
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType) {
            0 -> { // Title for popular actors
                val layoutInflater = LayoutInflater.from(parent.context)
                val cellForRow = layoutInflater.inflate(R.layout.recycler_list_title_header, parent,  false)
                return TitleHeaderViewHolder(cellForRow)
            }
            1 -> { // Popular actors list
                val layoutInflater = LayoutInflater.from(parent.context)
                val cellForRow = layoutInflater.inflate(R.layout.home_people_list, parent,  false)
                return PersonViewHolder(cellForRow)
            }
            2 -> { // Backdrop movie list
                val layoutInflater = LayoutInflater.from(parent.context)
                val cellForRow = layoutInflater.inflate(R.layout.home_backdrop_list, parent,  false)
                return BackdropViewHolder(cellForRow)
            }
            3 -> { // Title for latest movie list
                val layoutInflater = LayoutInflater.from(parent.context)
                val cellForRow = layoutInflater.inflate(R.layout.recycler_list_title_header, parent,  false)
                return TitleHeaderViewHolder(cellForRow)
            }
            else -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val cellForRow = layoutInflater.inflate(R.layout.recycler_movie_movie, parent,  false)
                return MovieViewHolder(cellForRow)
            }
        }
    }

    inner class TitleHeaderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val titleHeaderTextView: TextView = itemView.titleHeaderTextView

        fun bindView(title: String) {
            titleHeaderTextView.text = title
        }
    }


    inner class PersonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val recyclerView: RecyclerView = itemView.home_people_list
        private val recyclerViewShimmerPlaceholder: ShimmerFrameLayout = itemView.shimmer_people_view_container

        fun bindView() {
            // Set people list
            peopleAdapter = PeopleAdapter()
            peopleAdapter.people = people
            recyclerView.adapter = peopleAdapter
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)

            // Stop shimmer placeholder animation
            peopleAdapter.isCompleteLoadData { isComplete ->
                if (isComplete) {
                    recyclerViewShimmerPlaceholder.stopShimmer()
                    recyclerViewShimmerPlaceholder.visibility = View.GONE
                }
            }

        }
    }

    inner class BackdropViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val recyclerView: RecyclerView = itemView.home_company_list
        private val recyclerViewShimmerPlaceholder: ShimmerFrameLayout = itemView.shimmer_backdrop_view_container

        fun bindView() {
            movieBackDropAdapter = MovieBackDropAdapter()
            movieBackDropAdapter.movies = movies?.reversed()
            recyclerView.adapter = movieBackDropAdapter
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)

            movieBackDropAdapter.isCompleteLoadData { isComplete ->
                if (isComplete) {
                    recyclerViewShimmerPlaceholder.stopShimmer()
                    recyclerViewShimmerPlaceholder.visibility = View.GONE
                }
            }
        }
    }

    inner class MovieViewHolder(itemView: View, var movie: Movie? = null): RecyclerView.ViewHolder(itemView) {

        private val homeMovieImageView = itemView.homeMovieImageView
        private val homeMovieTextView = itemView.homeMovieTextView
        private val homeOverviewMovieTextView = itemView.homeOverviewMovieTextView
        private val homeAdultMovieTextView = itemView.homeAdultMovieTextView
        private val homeVoteMovieTextView = itemView.homeVoteMovieTextView

        fun bindView(movie: Movie?) {
            val imageUrl = "$IMAGE_BASE_URL${movie?.posterPath}"
            Picasso.get()
                .load(imageUrl)
                .into(homeMovieImageView)
            homeMovieTextView.text = movie?.title
            homeOverviewMovieTextView.text = movie?.overview
            homeAdultMovieTextView.visibility = if (movie?.isAdult ?: false) View.VISIBLE else View.INVISIBLE
            homeVoteMovieTextView.text = movie?.voteAverage.toString()
            homeVoteMovieTextView.visibility = View.VISIBLE
        }

        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, MovieDetailActivity::class.java)
                intent.putExtra(MovieDetailActivity.MOVIE_DETAIL_ID_KEY, movie?.id)
                intent.putExtra(MovieDetailActivity.MOVIE_DETAIL_TITLE_KEY, movie?.title)
                intent.putExtra(MovieDetailActivity.MOVIE_DETAIL_POPULARITY, movie?.popularity)
                itemView.context.startActivity(intent)
            }
        }
    }
}