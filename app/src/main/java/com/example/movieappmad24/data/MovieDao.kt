package com.example.movieappmad24.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieImage
import com.example.movieappmad24.models.MovieWithImages
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(movie: Movie): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieImage: MovieImage)

    @Update
    suspend fun update(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("SELECT * from movie WHERE id = :id")
    fun get(id: String): Flow<Movie?>

    @Query("SELECT * from movie")
    fun getAll(): Flow<List<Movie>>

    @Query("SELECT * from movie WHERE isFavorite = 1")
    fun getFavorites(): Flow<List<Movie>>

    @Transaction
    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovieWithImagesById(id: String): Flow<MovieWithImages?>

    @Transaction
    @Query("SELECT * FROM movie")
    fun getAllMoviesWithImages(): Flow<List<MovieWithImages>>

    @Transaction
    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun getFavoriteMoviesWithImages(): Flow<List<MovieWithImages>>

    @Query("UPDATE movie SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteStatus(id: String, isFavorite: Boolean)
}


