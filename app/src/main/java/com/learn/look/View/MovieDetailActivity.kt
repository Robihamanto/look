package com.learn.look.View

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.learn.look.Adapter.CompanyAdapter
import com.learn.look.Adapter.MovieDetailViewModel
import com.learn.look.Model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*



class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE_DETAIL_ID_KEY = "MovieDetaiIdlKey"
        const val MOVIE_DETAIL_TITLE_KEY = "MovieDetailTitleKey"
        const val MOVIE_DETAIL_POPULARITY = "MovieDetailPopularity"
    }

    private var movieDetailViewModel: MovieDetailViewModel = MovieDetailViewModel()
    private lateinit var companyAdapter: CompanyAdapter

    var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.learn.look.R.layout.activity_movie_detail)
        val movieId = intent.getIntExtra(MOVIE_DETAIL_ID_KEY, -1)
        setupCompaniesList()
        setupNavTitle()
        requestDetailMovie(movieId)
    }

    private fun requestDetailMovie(movieId: Int) {
        movieDetailViewModel.getMovie(movieId).observe(this, Observer {
            this.movie = it!!
            setupMovieUI()
            companyAdapter.companies = movie?.productionCompanies
            runOnUiThread {
                companyAdapter.notifyDataSetChanged()
            }
        })
    }


    private fun setupCompaniesList() {
        company_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        companyAdapter = CompanyAdapter()
        company_list.adapter = companyAdapter
    }

    private fun setupNavTitle() {
        val navBarTitle = intent.getStringExtra(MOVIE_DETAIL_TITLE_KEY)
        val navBarSubTitle = intent.getDoubleExtra(MOVIE_DETAIL_POPULARITY, 0.0)
//        supportActionBar?.title = navBarTitle
//        supportActionBar?.subtitle = "Popularity : $navBarSubTitle"
    }

    private fun setupMovieUI() {
        val imageUrl = "https://image.tmdb.org/t/p/w500${movie?.posterPath}"
        Picasso
            .get()
            .load(imageUrl)
            .placeholder(this.resources.getIdentifier("no_image", "drawable", this.packageName))
            .into(movieDetailImageView)
        movieDetailTitleTextView.text           = movie?.title
        movieDetailOverviewTextView.text        = movie?.overview
        movieDetailVoteAverageTextView.text     = movie?.voteAverage.toString()
        movieDetailCountryTextView.text         = "${movie?.productionCountry?.first()?.iso_3166_1}, " +
                "${movie?.releaseDate?.subSequence(0, 4)} " +
                "${getDuration(movie?.runtime ?: 0)}"//USA, 2019 / 2h 30 min
    }

    private fun getDuration(t: Int): String {
        val hours = t / 60 //since both are ints, you get an int
        val minutes = t % 60
        return "${hours}h ${minutes} min"
    }
}
