package com.example.moviemania.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviemania.databinding.UpcomingmoviesBinding
import com.example.moviemania.pojo.ResultX

class UpComingMovieAdapter():RecyclerView.Adapter<UpComingMovieAdapter.UpComingMovieViewHolder>() {

    lateinit var onItemClick:((ResultX) -> Unit)

    val baseUrl = "https://image.tmdb.org/t/p/w500/"

    private var upComingList = ArrayList<ResultX>()

    fun setUpComing(upComingList:ArrayList<ResultX>){
        this.upComingList = upComingList
        notifyDataSetChanged()
    }

    class UpComingMovieViewHolder(val binding:UpcomingmoviesBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpComingMovieViewHolder {
        return UpComingMovieViewHolder(UpcomingmoviesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return upComingList.size
    }

    override fun onBindViewHolder(holder: UpComingMovieViewHolder, position: Int) {
        val posterUrl = baseUrl + upComingList[position].poster_path

        Glide.with(holder.itemView.context)
            .load(posterUrl)
            .into(holder.binding.ivUpComingMoviePoster)

        holder.binding.tvUpComingMovieTitle.text = upComingList[position].title
        holder.binding.tvUpComingMovieDate.text = upComingList[position].release_date

        holder.itemView.setOnClickListener {
            onItemClick.invoke(upComingList[position])
        }
    }
}