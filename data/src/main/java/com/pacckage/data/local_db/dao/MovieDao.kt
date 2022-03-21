package com.pacckage.data.local_db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pacckage.data.local_db.model.ResultsEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovie(movie: List<ResultsEntity>)

    @Query("SELECT * FROM popular_movie")
    fun getPopularMovie(): PagingSource<Int, ResultsEntity>

    @Query("DELETE FROM popular_movie")
    suspend fun clearAll()

}