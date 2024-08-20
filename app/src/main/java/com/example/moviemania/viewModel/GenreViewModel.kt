package com.example.moviemania.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviemania.pojo.Genre
import com.example.moviemania.pojo.MoviesDetails
import com.example.moviemania.pojo.MoviesList
import com.example.moviemania.pojo.Result
import com.example.moviemania.pojo.ResultX
import com.example.moviemania.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenreViewModel: ViewModel() {
    val movieLiveData = MutableLiveData<List<Result>>()

    val apiKey = "aa8ce92f8c02785006a6e448841fe66e"

    fun getMoviesByGenre(genreId:String){
        RetrofitInstance.api.getMoviesByGenre(apiKey,genreId).enqueue(object : Callback<MoviesList>{
            override fun onResponse(call: Call<MoviesList>, response: Response<MoviesList>) {
                response.body()?.let {movie ->
                    movieLiveData.postValue(movie.results)
                }
            }

            override fun onFailure(call: Call<MoviesList>, t: Throwable) {
                Log.d("HomeFragment",t.message.toString())
            }

        })
    }
    fun observeGenreLiveData(): LiveData<List<Result>>{
        return movieLiveData
    }
}