package com.example.moviemania.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviemania.R
import com.example.moviemania.pojo.Cast

class ActorAdapter(private val actors: List<Cast>) : RecyclerView.Adapter<ActorAdapter.ActorViewHolder>() {

    class ActorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgActor: ImageView = view.findViewById(R.id.imgActor)
        val tvActorName: TextView = view.findViewById(R.id.tvActorName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_actor, parent, false)
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        val actor = actors[position]
        holder.tvActorName.text = actor.name
        val posterUrl = "https://image.tmdb.org/t/p/w500" + actor.profile_path
        Glide.with(holder.itemView.context)
            .load(posterUrl)
            .into(holder.imgActor)
    }

    override fun getItemCount(): Int {
        return actors.size
    }
}