package com.example.kotlin_omdbapi2.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_omdbapi2.R
import com.example.kotlin_omdbapi2.databinding.ItemMovieBinding
import com.example.kotlin_omdbapi2.model.MovieResults
import com.example.kotlin_omdbapi2.ui.home.HomeFragmentDirections
import kotlinx.android.synthetic.main.item_movie.view.*


class MoviesAdapter (val movieList: ArrayList<MovieResults>): RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>(),MovieClickListener {

    class MovieViewHolder(var view: ItemMovieBinding): RecyclerView.ViewHolder(view.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.item_game,parent,false)
        val view = DataBindingUtil.inflate<ItemMovieBinding>(inflater,
            R.layout.item_movie,parent,false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
       holder.view.movie = movieList[position]
        holder.view.listener = this
        /*  holder.view.gameName.text = gameList[position].name
          holder.view.gameRating.text = gameList[position].rating.toString()
          holder.view.gameReleased.text = gameList[position].released

          holder.view.setOnClickListener{
              val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(gameList[position].uuid)
              Navigation.findNavController(it).navigate(action)
          }

          holder.view.imageView.downloadFromUrl(gameList[position].backgroundImage,
              placeHolderProgressBar(holder.view.context))
          */
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateGameList(newGameList: List<MovieResults>){
        movieList.clear()
        movieList.addAll(newGameList)
        notifyDataSetChanged()
    }
    override fun onMovieClicked(v: View) {
        val uuid = v.movieUuidText.text.toString().toInt()
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }


}