package com.example.moviemania.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviemania.databinding.MoviesViewBinding
import com.example.moviemania.pojo.Result


class NewMoviesAdapter(): RecyclerView.Adapter<NewMoviesAdapter.NewMovieViewHolder>(){

    val baseUrl = "https://image.tmdb.org/t/p/w500/"

    lateinit var onItemClick:((Result) -> Unit)
    private var newMovieList = ArrayList<Result>()

    fun setNewMovies(newMovieList: ArrayList<Result>){
        this.newMovieList = newMovieList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewMovieViewHolder {
        return NewMovieViewHolder(MoviesViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return newMovieList.size
    }

    override fun onBindViewHolder(holder: NewMovieViewHolder, position: Int) {
        val posterUrl = baseUrl + newMovieList[position].poster_path
        Glide.with(holder.itemView.context)
            .load(posterUrl)
            .into(holder.binding.ivPoster)

        holder.binding.tvTitleTxt.text = newMovieList[position].title

        val movie = newMovieList[position]
        val formattedRating = String.format("%.1f", movie.vote_average)
        holder.binding.tvScore.text = formattedRating

        holder.itemView.setOnClickListener {
            onItemClick.invoke(newMovieList[position])

        }
    }

    class NewMovieViewHolder(val binding: MoviesViewBinding): RecyclerView.ViewHolder(binding.root)
}