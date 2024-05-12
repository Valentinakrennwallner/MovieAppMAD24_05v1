package com.example.movieappmad24.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.movieappmad24.data.MovieDao
import com.example.movieappmad24.data.MovieDatabase
import com.example.movieappmad24.models.MovieImage
import com.example.movieappmad24.models.getMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val database = MovieDatabase.getDatabase(applicationContext)
        val movieDao = database.movieDao()
        seedDatabase(movieDao)
        Result.success()
    }

    private suspend fun seedDatabase(movieDao: MovieDao) {
        val movies = getMovies()
        for (movie in movies) {
            val movieId = movieDao.add(movie)  // Capture the ID of the inserted movie
            for (image in movie.images) {
                val movieImage = MovieImage(movieId = movieId, url = image)
                movieDao.insert(movieImage)
            }
        }
    }
}