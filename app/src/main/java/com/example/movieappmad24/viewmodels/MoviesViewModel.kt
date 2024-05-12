package com.example.movieappmad24.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.data.MovieRepository
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieWithImages
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

// Inherit from ViewModel class
class MoviesViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _favoriteMovies = MutableStateFlow<List<MovieWithImages>>(emptyList())
    val favoriteMovies: StateFlow<List<MovieWithImages>> = _favoriteMovies.asStateFlow()

    init {
        loadFavoriteMovies()
    }

    private fun loadFavoriteMovies() {
        viewModelScope.launch {
            repository.getFavoriteMoviesWithImages()
                .distinctUntilChanged()
                .collect { movies ->
                    _favoriteMovies.value = movies
                }
        }
    }

    fun toggleFavoriteMovie(movie: Movie) {
        viewModelScope.launch {
            repository.toggleFavoriteMovie(movie)
            // Reload favorite movies to update the state
            loadFavoriteMovies()
        }
    }
}