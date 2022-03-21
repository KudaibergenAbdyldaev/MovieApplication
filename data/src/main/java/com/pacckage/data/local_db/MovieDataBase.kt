package com.pacckage.data.local_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pacckage.data.local_db.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDataBase : RoomDatabase() {

    lateinit var INSTANCE: MovieDataBase

    abstract fun userDao(): MovieDao

    companion object {
        @Volatile
        private var instance: MovieDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            MovieDataBase::class.java, "popular_movie.db"
        )
            .allowMainThreadQueries()
            .build()

    }

}