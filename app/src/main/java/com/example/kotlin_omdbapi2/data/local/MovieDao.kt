package com.example.kotlin_omdbapi2.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlin_omdbapi2.model.MovieResults

@Dao
interface MovieDao {

    @Insert
    suspend fun insertAll(vararg movies: MovieResults) : List<Long>

    @Query("SELECT * FROM movieResults")
    suspend fun getAllMovies() : List<MovieResults>

    @Query("SELECT * FROM movieResults WHERE uuid = :movieId")
    suspend fun getMovie(movieId : Int) : MovieResults

    @Query("DELETE FROM movieResults")
    suspend fun deleteAllMovies()
}