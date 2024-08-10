package com.example.moviemania.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.moviemania.activities.MovieActivity
import com.example.moviemania.adapter.NewMoviesAdapter
import com.example.moviemania.databinding.FragmentHomeBinding
import com.example.moviemania.pojo.Result
import com.example.moviemania.viewModel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMvvm: HomeViewModel
    private lateinit var randomMovie : Result
    private lateinit var newMoviesAdapter: NewMoviesAdapter
    val apiKey = "aa8ce92f8c02785006a6e448841fe66e"
    private val baseUrl = "https://image.tmdb.org/t/p/w500/"

    companion object{
        const val MOVIE_ID = "idMovie"
        const val MOVIE_TITLE = "titleMovie"
        const val MOVIE_POSTER = "posterMovie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMvvm = ViewModelProvider(this)[HomeViewModel::class.java]

        newMoviesAdapter = NewMoviesAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareNewMovieRecyclerView()

        homeMvvm.getRandomMovie()
        observerRandomMovie()
        onRandomMovieClick()

        homeMvvm.getNewMovies()
        observeNewMovieLiveData()
        onNewMovieClick()


    }


    private fun onNewMovieClick() {
        newMoviesAdapter.onItemClick = {movie ->
            val intent = Intent(activity,MovieActivity::class.java)
            intent.putExtra(MOVIE_ID,movie.id)
            intent.putExtra(MOVIE_TITLE,movie.title)
            intent.putExtra(MOVIE_POSTER,movie.poster_path)
            startActivity(intent)
        }
    }

    private fun prepareNewMovieRecyclerView() {
        binding.rvNewMovies.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
            adapter = newMoviesAdapter
        }
    }

    private fun observeNewMovieLiveData() {
        homeMvvm.observerNewMovieLiveData().observe(viewLifecycleOwner
        ) { movieList ->
                newMoviesAdapter.setNewMovies(newMovieList = movieList as ArrayList<Result>)
        }
    }

    private fun onRandomMovieClick() {
        binding.randomMovieCard.setOnClickListener{
            val intent = Intent(activity,MovieActivity::class.java)
            intent.putExtra(MOVIE_ID,randomMovie.id)
            intent.putExtra(MOVIE_POSTER,randomMovie.poster_path)
            intent.putExtra(MOVIE_TITLE,randomMovie.title)
            startActivity(intent)
        }

    }

    private fun observerRandomMovie() {
        homeMvvm.observeRandomMovieLiveData().observe(viewLifecycleOwner
        ) { value ->
            val posterUrl = baseUrl + value.poster_path
            Glide.with(this@HomeFragment)
                .load(posterUrl)
                .into(binding.imgRandomMovie)

            this.randomMovie = value
        }
    }
}



