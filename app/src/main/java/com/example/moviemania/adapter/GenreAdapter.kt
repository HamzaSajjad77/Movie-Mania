package com.example.moviemania.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemania.databinding.GenreLayoutBinding
import com.example.moviemania.pojo.Genre

class GenreAdapter(): RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {
    inner class GenreViewHolder(val binding: GenreLayoutBinding):RecyclerView.ViewHolder(binding.root)

    private var genreList = ArrayList<Genre>()
    var onItemClick : ((Genre) -> Unit)? = null

    fun setGenreList(genreList : List<Genre>){
        this.genreList = genreList as ArrayList<Genre>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(
            GenreLayoutBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.binding.tvGenre.text = genreList[position].name

        holder.itemView.setOnClickListener {
            onItemClick!!.invoke(genreList[position])
        }
    }
}