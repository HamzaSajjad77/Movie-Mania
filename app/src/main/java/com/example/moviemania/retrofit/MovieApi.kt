package com.example.moviemania.retrofit

import com.example.moviemania.pojo.MoviesDetails
import com.example.moviemania.pojo.MoviesList
import com.example.moviemania.pojo.UpComingMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular?")
    fun getMovies(@Query("api_key") apiKey: String) : Call<MoviesList>

    @GET("discover/movie?")
    fun getUpComingMovie(@Query("api_key") apiKey: String,
                         @Query("primary_release_date.gte") startDate: String,
                         @Query("primary_release_date.lte") endDate: String ): Call<UpComingMovie>

    @GET("discover/movie")
    fun getPopularMovies(@Query("api_key") apiKey: String,
                         @Query("vote_average.gte") minRating: Int = 8,
                         @Query("sort_by") sortBy: String = "vote_average.desc",
                         @Query("vote_count.gte") minVotes: Int = 1000) : Call<MoviesList>

    @GET("movie/{movie_id}")
    fun getMoviesDetail(@Path("movie_id") id: String,
    @Query("api_key") apiKey: String,
    @Query("append_to_response") appendToResponse: String = "credits") : Call<MoviesDetails>


    @GET("genre/movie/list")
    fun getMovieGenres(@Query("api_key") apiKey: String): Call<MoviesDetails>


}