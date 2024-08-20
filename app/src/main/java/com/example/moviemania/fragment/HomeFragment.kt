package com.example.moviemania.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.moviemania.activities.GenreActivity
import com.example.moviemania.activities.MainActivity
import com.example.moviemania.activities.MovieActivity
import com.example.moviemania.adapter.GenreAdapter
import com.example.moviemania.adapter.NewMoviesAdapter
import com.example.moviemania.adapter.UpComingMovieAdapter
import com.example.moviemania.databinding.FragmentHomeBinding
import com.example.moviemania.pojo.Result
import com.example.moviemania.viewModel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var randomMovie : Result
    private lateinit var newMoviesAdapter: NewMoviesAdapter
    private lateinit var upComingMovieAdapter: UpComingMovieAdapter
    private lateinit var genreAdapter: GenreAdapter
    private val baseUrl = "https://image.tmdb.org/t/p/w500/"

    companion object{
        const val MOVIE_ID = "idMovie"
        const val MOVIE_TITLE = "titleMovie"
        const val MOVIE_POSTER = "posterMovie"
        const val GENRE_ID = "genreId"
        const val GENRE_NAME = "genreName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        newMoviesAdapter = NewMoviesAdapter()
        upComingMovieAdapter = UpComingMovieAdapter()

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

        viewModel.getRandomMovie()
        observerRandomMovie()
        onRandomMovieClick()

        prepareNewMovieRecyclerView()
        prepareUpComingMovieRecyclerView()
        prepareGenreRecyclerView()

        viewModel.getNewMovies()
        observeNewMovieLiveData()
        onNewMovieClick()

        viewModel.getUpComingMovie()
        observeUpComingMovieLiveData()
        onUpcomingMovieClick()

        viewModel.getGenre()
        observeGenreLiveData()
        onGenreClick()

    }

    private fun onGenreClick() {
        genreAdapter.onItemClick = {genre ->
            val intent = Intent(activity,GenreActivity::class.java)
            intent.putExtra(GENRE_ID,genre.id)
            intent.putExtra(GENRE_NAME,genre.name)
            startActivity(intent)

        }
    }

    private fun prepareGenreRecyclerView() {
        genreAdapter = GenreAdapter()
        binding.rvGenre.apply {
            layoutManager = GridLayoutManager(context, 3,GridLayoutManager.VERTICAL,false)
            adapter = genreAdapter
        }
    }

    private fun observeGenreLiveData() {
        viewModel.observeGenreLiveData().observe(viewLifecycleOwner, Observer { genre ->
            genreAdapter.setGenreList(genre)
        })
    }

    private fun onUpcomingMovieClick() {
        upComingMovieAdapter.onItemClick = {movie ->
            val intent = Intent(activity,MovieActivity::class.java)
            intent.putExtra(MOVIE_ID,movie.id)
            intent.putExtra(MOVIE_TITLE,movie.title)
            intent.putExtra(MOVIE_POSTER,movie.poster_path)
            startActivity(intent)

        }
    }

    private fun prepareUpComingMovieRecyclerView() {
        binding.rvUpcomingMovies.apply {
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            adapter = upComingMovieAdapter
        }
    }

    private fun observeUpComingMovieLiveData() {
        viewModel.observeUpComingMovieLiveData().observe(viewLifecycleOwner
        ) { upComingList ->
                upComingMovieAdapter.setUpComing(upComingList = upComingList as ArrayList)
        }
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
        viewModel.observerNewMovieLiveData().observe(viewLifecycleOwner
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
        viewModel.observeRandomMovieLiveData().observe(viewLifecycleOwner
        ) { value ->
            val posterUrl = baseUrl + value.poster_path
            Glide.with(this@HomeFragment)
                .load(posterUrl)
                .into(binding.imgRandomMovie)

            this.randomMovie = value
        }
    }
}



