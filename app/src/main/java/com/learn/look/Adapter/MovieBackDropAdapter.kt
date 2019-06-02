package com.learn.look.Adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.learn.look.Model.Movie
import com.learn.look.R
import com.learn.look.Utils.IMAGE_BASE_URL
import com.learn.look.View.MovieDetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_list_movie_backdrop.view.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions




class MovieBackDropAdapter(var movies: List<Movie>? = null) : RecyclerView.Adapter<MovieBackDropAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return movies?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies?.get(position)
        holder.movie = movie
        holder.bindView(movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.recycler_list_movie_backdrop, parent,  false)
        return ViewHolder(cellForRow)
    }


    inner class ViewHolder(itemView: View, var movie: Movie? = null): RecyclerView.ViewHolder(itemView) {

        private val homeMovieBackdropImageView = itemView.homeMovieBackDropImageView
        private val homeMovieBackdropTextView = itemView.homeMovieBackDropTextView

        fun bindView(movie: Movie?) {
            val imageUrl = "$IMAGE_BASE_URL${movie?.backdropPath}"

            Glide.with(itemView)
                .load(imageUrl)
                .transform(RoundedCorners(20))
                .into(homeMovieBackdropImageView)

            homeMovieBackdropTextView.text = movie?.title
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