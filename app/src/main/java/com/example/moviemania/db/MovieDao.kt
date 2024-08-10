package com.example.moviemania.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviemania.pojo.Result

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertMovie(result: Result)

    @Delete
    suspend fun delete(result:Result)

    @Query("SELECT * FROM movieInformation")
    fun getAllMovies(): LiveData<List<Result>>



}