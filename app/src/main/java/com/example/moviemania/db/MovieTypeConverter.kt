package com.example.moviemania.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.moviemania.pojo.Credits
import com.example.moviemania.pojo.Genre
import com.example.moviemania.pojo.MoviesDetails
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson


@TypeConverters
class MovieTypeConverter {
    private val gson = Gson()

    // Convert List<Int> to String
    @TypeConverter
    fun fromListOfIntegersToString(integers: List<Int>?): String {
        return integers?.joinToString(separator = ",") ?: ""
    }

    // Convert String to List<Int>
    @TypeConverter
    fun fromStringToListOfIntegers(data: String?): List<Int> {
        return data?.split(",")?.map { it.toInt() } ?: emptyList()
    }

    // Convert MoviesDetails to String
    @TypeConverter
    fun fromMoviesDetails(moviesDetails: MoviesDetails?): String? {
        return moviesDetails?.let { gson.toJson(it) }
    }

    // Convert String to MoviesDetails
    @TypeConverter
    fun toMoviesDetails(moviesDetailsString: String?): MoviesDetails? {
        return moviesDetailsString?.let {
            val type = object : TypeToken<MoviesDetails>() {}.type
            gson.fromJson(it, type)
        }
    }


    @TypeConverter
    fun fromGenresListToString(genres: List<Genre>?): String {
        return genres?.joinToString(",") { it.name } ?: ""
    }

    @TypeConverter
    fun fromStringToGenres(value: String): List<Genre> {
        if (value.isEmpty()) {
            return emptyList()
        }
        return value.split(",").map {
            val (id, name) = it.split(":")
            Genre(id, name)
        }
    }

    @TypeConverter
    fun fromCreditsToString(credits: Credits?): String? {
        return credits?.let { gson.toJson(it) }
    }

    // Convert JSON string to Credits object
    @TypeConverter
    fun toCreditsFromString(data: String?): Credits? {
        return data?.let {
            val type = object : TypeToken<Credits>() {}.type
            gson.fromJson(it, type)
        }
    }

}