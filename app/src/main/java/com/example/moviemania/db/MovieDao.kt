package com.example.moviemania.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviemania.pojo.MoviesDetails
import com.example.moviemania.pojo.Result


@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertMovie(results: Result)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertMovieDetails(movieDetails: MoviesDetails)

    @Delete
    suspend fun delete(results: Result)

    @Delete
    suspend fun deleteMovieDetails(movieDetails: MoviesDetails)

    @Query("SELECT * FROM results")
    fun getAllMovies(): LiveData<List<Result>>

    @Query("SELECT * FROM movies_details WHERE id = :movieId")
    suspend fun getMovieDetails(movieId: String): MoviesDetails?


}