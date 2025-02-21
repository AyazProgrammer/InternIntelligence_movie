package com.example.movie.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movie.data.local.dao.GenreDao
import com.example.movie.data.local.dao.MovieDao
import com.example.movie.data.local.entity.GenreEntity
import com.example.movie.data.local.entity.MovieEntity

@Database(entities = [MovieEntity::class,GenreEntity::class], version = 5)
abstract class AppDatabase : RoomDatabase() {

    abstract fun MovieDao(): MovieDao
    abstract fun GenreDao(): GenreDao


}

object DatabaseInstance {
    private var appDatabase: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "movies1_database"
            ).build()
        }
        return appDatabase!!
    }
}
