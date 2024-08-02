package com.example.appmovies.data.remote

import com.example.appmovies.application.AppConstants
import com.example.appmovies.data.model.MovieList
import com.example.appmovies.repository.WebService

class MovieDataSource(private val webService: WebService) {
    suspend fun getUpcomingMovies(): MovieList = webService.getUpcomingMovies(AppConstants.API_KEY)
    suspend fun getTopRatedMovies(): MovieList = webService.getTopRatedMovies(AppConstants.API_KEY)
    suspend fun getPopularMovies(): MovieList = webService.getPopularMovies(AppConstants.API_KEY)
}