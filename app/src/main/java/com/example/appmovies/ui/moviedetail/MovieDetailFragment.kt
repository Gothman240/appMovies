package com.example.appmovies.ui.moviedetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.appmovies.R
import com.example.appmovies.databinding.FragmentMovieDetailBinding


class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private lateinit var binding: FragmentMovieDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailBinding.bind(view)
    }

}