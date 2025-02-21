package com.example.movie.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Genres")
data class GenreEntity(
    @PrimaryKey val id: Int,
    val name: String,
)