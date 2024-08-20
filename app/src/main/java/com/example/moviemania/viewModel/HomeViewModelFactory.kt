package com.example.moviemania.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviemania.db.MovieDataBase

class HomeViewModelFactory(
    private val movieDataBase: MovieDataBase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(movieDataBase) as T
    }
}