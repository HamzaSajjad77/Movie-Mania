package com.example.moviemania.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviemania.databinding.MoviesGenreViewBinding
import com.example.moviemania.pojo.Genre
import com.example.moviemania.pojo.MoviesDetails
import com.example.moviemania.pojo.Result
import java.util.ArrayList

class GenreMovieListAdapter : RecyclerView.Adapter<GenreMovieListAdapter.GenreMovieListViewModel>(){
    val baseUrl = "https://image.tmdb.org/t/p/w500/"

   private var movieList = ArrayList<Result>()

    fun setMovieList(movieList: List<Result>){
        this.movieList = movieList as ArrayList<Result>
        notifyDataSetChanged()
    }

    inner class GenreMovieListViewModel(val binding: MoviesGenreViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreMovieListViewModel {
        return GenreMovieListViewModel(
            MoviesGenreViewBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: GenreMovieListViewModel, position: Int) {
        Glide.with(holder.itemView).load(movieList[position]).into(holder.binding.ivPoster)
        holder.binding.tvTitleTxt.text = movieList[position].title
        val posterUrl = baseUrl + movieList[position].poster_path
        Glide.with(holder.itemView.context)
            .load(posterUrl)
            .into(holder.binding.ivPoster)

        val movie = movieList[position]
        val formattedRating = String.format("%.1f", movie.vote_average)
        holder.binding.tvScore.text = formattedRating

    }

}