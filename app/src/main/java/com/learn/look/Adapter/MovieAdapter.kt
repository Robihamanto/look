package com.learn.look.Adapter

import android.content.Intent
import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.learn.look.Model.Movie
import com.learn.look.R
import com.learn.look.View.MovieDetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_list_movie.view.*

class MovieAdapter(var movies: List<Movie>? = null): RecyclerView.Adapter<MovieAdapter.ViewHolder>(){

    override fun getItemCount(): Int {
        return  movies?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRowAt = layoutInflater.inflate(R.layout.recycler_list_movie, parent, false)
        return ViewHolder(cellForRowAt)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies!!.get(position)
        holder.movie = movie
        holder.bindView(movie)
    }


    inner class ViewHolder(itemView: View, var movie: Movie? = null): RecyclerView.ViewHolder(itemView) {

        val movieImageView = itemView.movieImageView

        fun bindView(movie: Movie) {
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .into(movieImageView)
            movieImageView.layoutParams.height = Resources.getSystem().displayMetrics.heightPixels / 2
            movieImageView.requestLayout()
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