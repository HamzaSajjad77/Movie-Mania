package com.example.moviemania.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviemania.pojo.MoviesList
import com.example.moviemania.pojo.Result
import com.example.moviemania.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel:ViewModel() {

    private var randomMovieLiveData = MutableLiveData<Result>()
    val apiKey = "aa8ce92f8c02785006a6e448841fe66e"

    private var newMoviesLiveData = MutableLiveData<List<Result>>()


    fun getRandomMovie(){

        RetrofitInstance.api.getPopularMovies(apiKey).enqueue(object : Callback<MoviesList> {
            override fun onResponse(
                call: Call<MoviesList>,
                response: Response<MoviesList>
            ) {
                if (response.body() != null) {
                    val newMovie: Result = response.body()!!.results.random()
                    randomMovieLiveData.value = newMovie

                } else {
                    Log.d("HomeFragment", "Response body is null")
                }
            }

            override fun onFailure(call: Call<MoviesList>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }
        })
    }

    fun getNewMovies(){
        RetrofitInstance.api.getMovies(apiKey).enqueue(object :Callback<MoviesList>{
            override fun onResponse(call: Call<MoviesList>, response: Response<MoviesList>) {
                if (response.body() != null){
                    newMoviesLiveData.value = response.body()!!.results
                }
            }

            override fun onFailure(call: Call<MoviesList>, t: Throwable) {
                Log.d("HomeFragment",t.message.toString())
            }
        })
    }


    fun observeRandomMovieLiveData():LiveData<Result>{
        return randomMovieLiveData
    }

    fun observerNewMovieLiveData():LiveData<List<Result>>{
        return newMoviesLiveData
    }

}