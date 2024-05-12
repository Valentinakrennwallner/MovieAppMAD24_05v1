package com.example.movieappmad24.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.data.MovieRepository
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieWithImages
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WatchlistViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    val favoriteMovies: Flow<List<MovieWithImages>> = movieRepository.getFavoriteMoviesWithImages()

    fun toggleFavoriteMovie(movieWithImages: MovieWithImages) {
        val movie = movieWithImages.movie
        viewModelScope.launch {
            val updatedMovie = movie.copy(isFavorite = !movie.isFavorite)
            movieRepository.updateMovie(updatedMovie)
        }
    }
}