package com.example.moviemania.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey


data class MoviesList(
    val results: List<Result>
)
@Entity(tableName = "movies_details")
data class MoviesDetails(
    val budget: Int,
    val genres: List<Genre>,
    val genre_ids: List<Int>,
    val homepage: String,
    @PrimaryKey val id: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val status: String,
    val title: String,
    val video: Boolean,
    val vote_average: Float,
    val vote_count: Int,
    val credits: Credits
)
data class Credits(
    val cast: List<Cast>
)

data class Cast(
    val adult: Boolean,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val gender: Int,
    val id: String,
    val known_for_department: String,
    val name: String,
    val order: Int,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)
