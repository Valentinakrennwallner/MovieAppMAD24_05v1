package com.example.movieappmad24.data

import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieWithImages
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val movieDao: MovieDao) {

    suspend fun addMovie(movie: Movie) = movieDao.add(movie)

    suspend fun updateMovie(movie: Movie) = movieDao.update(movie)

    suspend fun deleteMovie(movie: Movie) = movieDao.delete(movie)

    fun getAllMovies(): Flow<List<Movie>> = movieDao.getAll()

    fun getFavoriteMovies(): Flow<List<Movie>> = movieDao.getFavorites()

    fun getById(id: String): Flow<Movie?> = movieDao.get(id)

    fun getAllMoviesWithImages(): Flow<List<MovieWithImages>> = movieDao.getAllMoviesWithImages()

    fun getFavoriteMoviesWithImages(): Flow<List<MovieWithImages>> = movieDao.getFavoriteMoviesWithImages()

    fun getMovieWithImagesById(id: String): Flow<MovieWithImages?> = movieDao.getMovieWithImagesById(id)

    suspend fun toggleFavoriteMovie(movie: Movie) {
        val updatedFavoriteStatus = !movie.isFavorite
        movieDao.updateFavoriteStatus(movie.id, updatedFavoriteStatus)
    }
}