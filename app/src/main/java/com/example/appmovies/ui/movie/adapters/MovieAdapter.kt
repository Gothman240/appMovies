package com.example.appmovies.ui.movie.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appmovies.core.BaseViewHolder
import com.example.appmovies.data.model.Movie
import com.example.appmovies.databinding.MovieItemBinding
import com.example.appmovies.presentation.MovieViewModel

class MovieAdapter(
    private val moviesList: List<Movie>,
    private val itemClickListener: OnMovieClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnMovieClickListener {
        fun onMovieClick(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {

        val itemBinding =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //cargar el layout
        val holder = MoviesViewHolder(itemBinding, parent.context)

        //obtener la posicion del item clickeado
        itemBinding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf {
                it != DiffUtil.DiffResult.NO_POSITION
            }
                ?: return@setOnClickListener //si es nullo no hace nada
            itemClickListener.onMovieClick(moviesList[position]) //devuelve la posicion del item clickeado
        }

        return holder
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    //cada uno de los datos se dibuja en pantalla
    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MoviesViewHolder -> holder.bind(moviesList[position])
        }
    }

    //clase interna que se encarga de ponerle la imagen a cada uno de los elementos
    private inner class MoviesViewHolder(val binding: MovieItemBinding, val context: Context) :
        BaseViewHolder<Movie>(binding.root) {
        override fun bind(item: Movie) {
            Glide.with(context).load("https://image.tmdb.org/t/p/w500/${item.poster_path}")
                .centerCrop() //redimensionar imagen
                .into(binding.imgMovie) //cargar imagen con el id (.xml) de ImgView
        }

    }
}