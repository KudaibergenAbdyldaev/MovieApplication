package com.pacckage.data.local_db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular_movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "page")
    val page: Int,
    @ColumnInfo(name = "results")
    val results: List<ResultsEntity>
)