package com.example.movieappmad24.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.data.MovieRepository
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieWithImages
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DetailsViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getMovieWithImagesById(movieId: String): Flow<MovieWithImages?> {
        return movieRepository.getMovieWithImagesById(movieId)
    }

    fun toggleFavoriteMovie(movie: Movie) {
        viewModelScope.launch {
            val updatedMovie = movie.copy(isFavorite = !movie.isFavorite)
            movieRepository.updateMovie(updatedMovie)
        }
    }
}


