package com.learn.look.Adapter

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.learn.look.Model.Company
import com.learn.look.Model.Movie
import com.learn.look.Model.Person
import com.learn.look.R
import com.learn.look.Utils.IMAGE_BASE_URL
import com.learn.look.View.MovieDetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.home_backdrop_list.view.*
import kotlinx.android.synthetic.main.home_people_list.view.*
import kotlinx.android.synthetic.main.recycler_movie_movie.view.*
import kotlinx.android.synthetic.main.reycler_list_company.view.*

class HomeAdapter(var people: List<Person>? = null, var companies: List<Company>? = null, var movies: List<Movie>? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var peopleAdapter: PeopleAdapter
    private lateinit var movieBackDropAdapter: MovieBackDropAdapter

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (position) {
            0 -> {
                val view = holder as PersonViewHolder
                view.bindView()
            }
            1 -> {
                val view = holder as BackdropViewHolder
                view.bindView()
            }
            else -> {
                val view = holder as MovieViewHolder
                val movie = movies?.get(position - 2)
                view.movie = movie
                view.bindView(movie)
            }
        }
    }

    override fun getItemCount(): Int {
        return movies?.size ?: 2 + 2
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType) {
            0 -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val cellForRow = layoutInflater.inflate(R.layout.home_people_list, parent,  false)
                return PersonViewHolder(cellForRow)
            }
            1 -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val cellForRow = layoutInflater.inflate(R.layout.home_backdrop_list, parent,  false)
                return BackdropViewHolder(cellForRow)
            } else -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val cellForRow = layoutInflater.inflate(R.layout.recycler_movie_movie, parent,  false)
                return MovieViewHolder(cellForRow)
            }
        }
    }


    inner class PersonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val recyclerView: RecyclerView = itemView.home_people_list

        fun bindView() {
            peopleAdapter = PeopleAdapter()
            peopleAdapter.people = people
            recyclerView.adapter = peopleAdapter
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    inner class BackdropViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val recyclerView: RecyclerView = itemView.home_company_list

        fun bindView() {
            movieBackDropAdapter = MovieBackDropAdapter()
            movieBackDropAdapter.movies = movies?.reversed()
            recyclerView.adapter = movieBackDropAdapter
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    inner class MovieViewHolder(itemView: View, var movie: Movie? = null): RecyclerView.ViewHolder(itemView) {

        private val homeMovieImageView = itemView.homeMovieImageView
        private val homeMovieTextView = itemView.homeMovieTextView
        private val homeOverviewMovieTextView = itemView.homeOverviewMovieTextView
        private val homeAdultMovieTextView = itemView.homeAdultMovieTextView

        fun bindView(movie: Movie?) {
            val imageUrl = "$IMAGE_BASE_URL${movie?.posterPath}"
            Picasso.get()
                .load(imageUrl)
                .into(homeMovieImageView)
            homeMovieTextView.text = movie?.title
            homeOverviewMovieTextView.text = movie?.overview
            homeAdultMovieTextView.visibility = if (movie?.isAdult ?: false) View.VISIBLE else View.INVISIBLE
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