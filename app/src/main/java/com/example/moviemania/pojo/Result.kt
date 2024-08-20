package com.example.moviemania.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "results")
data class Result(
    val adult: Boolean?,
    val backdrop_path: String?,
    val genre_ids: List<Int>?,
    @PrimaryKey val id: String,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?,
    val details: MoviesDetails?
)