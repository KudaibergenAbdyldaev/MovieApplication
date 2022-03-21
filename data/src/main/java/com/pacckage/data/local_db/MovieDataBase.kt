package com.pacckage.data.local_db

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pacckage.data.local_db.dao.MovieDao
import com.pacckage.data.local_db.dao.RemoteKeysDao
import com.pacckage.data.local_db.model.RemoteKeys
import com.pacckage.data.local_db.model.ResultsEntity

@Database(entities = [ResultsEntity::class, RemoteKeys::class], version = 2, exportSchema = false)
abstract class MovieDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun remoteKeyDao(): RemoteKeysDao

    companion object {
        private var INSTANCE: MovieDataBase? = null
        private val LOCK = Any()
        private const val DB_NAME = "popular_movie.db"

        fun getInstance(application: Application): MovieDataBase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    MovieDataBase::class.java,
                    DB_NAME
                ).fallbackToDestructiveMigration().build()
                INSTANCE = db
                return db
            }
        }

    }

}