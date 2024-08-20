package com.example.moviemania.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviemania.adapter.GenreMovieListAdapter
import com.example.moviemania.databinding.ActivityGenreBinding
import com.example.moviemania.fragment.HomeFragment
import com.example.moviemania.viewModel.GenreViewModel

class GenreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGenreBinding
    lateinit var genreViewModel : GenreViewModel
    lateinit var genreAdapter: GenreMovieListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepareRecyclerView()

        genreViewModel = ViewModelProvider(this)[GenreViewModel::class.java]

        genreViewModel.getMoviesByGenre(intent.getStringExtra(HomeFragment.GENRE_ID)!!)

        val genreName = intent.getStringExtra(HomeFragment.GENRE_NAME) ?: "Genre"
        binding.tvGenreCount.text = genreName

        genreViewModel.observeGenreLiveData().observe(this, Observer { movie ->
            genreAdapter.setMovieList(movie)
        })
    }


    private fun prepareRecyclerView() {
        genreAdapter = GenreMovieListAdapter()
        binding.rvGenre.apply {
            layoutManager  = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter = genreAdapter
        }
    }


}