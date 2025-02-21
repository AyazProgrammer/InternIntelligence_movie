package com.example.movie.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movie.data.local.entity.GenreEntity
import com.example.movie.data.local.entity.MovieEntity
@Dao
interface GenreDao {

    @Query("SELECT * FROM genres")
    suspend fun getAllGenres(): List<GenreEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(movies:List<GenreEntity>)

    @Delete
    suspend fun deleteGenres(movie: GenreEntity)
}