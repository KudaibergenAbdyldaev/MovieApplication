package com.pacckage.data.local_db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pacckage.data.local_db.model.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMovie(movie: MovieEntity)

    @Query("SELECT * FROM 'popular_movie'")
    fun getPopularMovie(): PagingSource<Int, MovieEntity>

}