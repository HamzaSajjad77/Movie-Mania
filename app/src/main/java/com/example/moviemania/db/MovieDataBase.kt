package com.example.moviemania.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviemania.pojo.MoviesDetails
import com.example.moviemania.pojo.Result

@Database([Result::class, MoviesDetails::class],
            version = 1,
            exportSchema = false)
@TypeConverters(MovieTypeConverter::class)
abstract class MovieDataBase: RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        var INSTANCE: MovieDataBase? = null

        @Synchronized
        fun getInstance(context: Context): MovieDataBase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    MovieDataBase::class.java,
                    "movie.db").fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as MovieDataBase
        }
    }
}