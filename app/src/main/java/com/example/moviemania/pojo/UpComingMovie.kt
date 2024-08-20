package com.example.moviemania.pojo

data class UpComingMovie(
    val dates: Dates,
    val page: Int,
    val results: List<ResultX>,
    val total_pages: Int,
    val total_results: Int
)