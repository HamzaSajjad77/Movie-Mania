package com.example.moviemania.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviemania.db.MovieDataBase
import com.example.moviemania.pojo.MoviesDetails
import com.example.moviemania.pojo.Result
import com.example.moviemania.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel():ViewModel() {
    private var movieDetailLiveData = MutableLiveData<MoviesDetails>()
    val apiKey = "aa8ce92f8c02785006a6e448841fe66e"


    fun getMovieDetail(id: String, apiKey: String){
        RetrofitInstance.api.getMoviesDetail(id,apiKey).enqueue(object : Callback<MoviesDetails>{
            override fun onResponse(
                call: Call<MoviesDetails>,
                response: Response<MoviesDetails>
            ) {
               if (response.body() != null){
                   movieDetailLiveData.value = response.body()!!
               }else{
                   return
               }
            }

            override fun onFailure(call: Call<MoviesDetails>, t: Throwable) {
                Log.d("MovieActivity", t.message.toString())
            }

        })
    }

    fun observeMovieDetailLiveData(): LiveData<MoviesDetails> {
        return movieDetailLiveData
    }

}