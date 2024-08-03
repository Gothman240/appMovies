package com.example.appmovies.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.appmovies.R
import com.example.appmovies.core.Resource
import com.example.appmovies.data.remote.MovieDataSource
import com.example.appmovies.databinding.FragmentMovieBinding
import com.example.appmovies.presentation.MovieViewModel
import com.example.appmovies.presentation.MovieViewModelFactory
import com.example.appmovies.repository.MovieRepositoryImpl
import com.example.appmovies.repository.RetrofilClient


class MovieFragment : Fragment(R.layout.fragment_movie) {

    private lateinit var binding: FragmentMovieBinding

    //inyeccion de dependencias manual
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryImpl(
                MovieDataSource(RetrofilClient.webService)
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Failure -> {
                    Log.d("LiveData", "${result.exception}")
                }
                is Resource.Loading -> {
                    Log.d("LiveData", "Loading...")
                }
                is Resource.Success -> {
                    Log.d("LiveData", "${result.data}")
                }
            }
        })
    }

}