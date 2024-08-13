package com.example.moviemania.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.moviemania.adapter.ActorAdapter
import com.example.moviemania.databinding.ActivityMovieBinding
import com.example.moviemania.db.MovieDataBase
import com.example.moviemania.fragment.HomeFragment
import com.example.moviemania.pojo.MoviesDetails
import com.example.moviemania.viewModel.MovieViewModel

class MovieActivity : AppCompatActivity() {
    private lateinit var movieId : String
    private lateinit var movieTitle: String
    private lateinit var moviePoster: String
    private lateinit var binding: ActivityMovieBinding
    private lateinit var movieMvvm: MovieViewModel
    private lateinit var actorAdapter: ActorAdapter

    val baseUrl = "https://image.tmdb.org/t/p/w500/"
    val apiKey = "aa8ce92f8c02785006a6e448841fe66e"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieMvvm = ViewModelProvider(this)[MovieViewModel::class.java]

        getMovieInfoFromIntent()
        setInfoInViews()
        loadingCase()
        movieMvvm.getMovieDetail(movieId, apiKey)
        observeMovieDetailsLiveData()
    }

    private fun setInfoInViews() {
        val posterUrl = baseUrl + moviePoster
        Glide.with(applicationContext)
            .load(posterUrl)
            .into(binding.posterNormalImg)

        Glide.with(applicationContext)
            .load(posterUrl)
            .into(binding.posterBigImg)

        binding.tvMovieTitle.text = movieTitle

    }

    private fun observeMovieDetailsLiveData() {
        movieMvvm.observeMovieDetailLiveData().observe(this,object :Observer<MoviesDetails>{
            override fun onChanged(value: MoviesDetails) {
                responseCase()
                binding.tvMovieDuration.text = if (value.runtime != null && value.runtime > 0) {
                    convertMinutesToHours(value.runtime)
                } else {
                    "N/A"
                }
                binding.tvMovieRating.text = if (value.vote_average != null && value.vote_average > 0) {
                    String.format("%.1f", value.vote_average)
                } else {
                    "N/A"
                }
                binding.tvMovieReleaseDate.text = value.release_date
                binding.tvMovieSummaryInfo.text = value.overview
                val mainActors = value.credits.cast.take(10)
                actorAdapter = ActorAdapter(mainActors)
                binding.rvActorsImages.adapter = actorAdapter
                binding.rvActorsImages.layoutManager = LinearLayoutManager(this@MovieActivity, LinearLayoutManager.HORIZONTAL, false)
            }
        })
    }

    private fun getMovieInfoFromIntent() {
        val intent = intent
        movieId = intent.getStringExtra(HomeFragment.MOVIE_ID)!!
        movieTitle = intent.getStringExtra(HomeFragment.MOVIE_TITLE)!!
        moviePoster = intent.getStringExtra(HomeFragment.MOVIE_POSTER)!!
    }
    private  fun convertMinutesToHours(minutes: Int): String {
        val hours = minutes / 60
        val remainingMinutes = minutes % 60
        return "${hours}h ${remainingMinutes}m"
    }

    private fun loadingCase(){
        binding.pbDetailFragmentLoading.visibility = View.VISIBLE
        binding.ivTopConstraintFav.visibility = View.INVISIBLE
        binding.tvMovieSummaryInfo.visibility = View.INVISIBLE
        binding.rvActorsImages.visibility = View.INVISIBLE
        binding.tvMovieDuration.visibility = View.INVISIBLE
        binding.tvMovieRating.visibility = View.INVISIBLE
        binding.tvMovieReleaseDate.visibility = View.INVISIBLE
    }
    private fun responseCase(){
        binding.pbDetailFragmentLoading.visibility = View.INVISIBLE
        binding.ivTopConstraintFav.visibility = View.VISIBLE
        binding.tvMovieSummaryInfo.visibility = View.VISIBLE
        binding.rvActorsImages.visibility = View.VISIBLE
        binding.tvMovieDuration.visibility = View.VISIBLE
        binding.tvMovieRating.visibility = View.VISIBLE
        binding.tvMovieReleaseDate.visibility = View.VISIBLE

    }
}