package com.pacckage.data.local_db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular_movie")
data class ResultsEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "movieId")
    val movieId: Int,
    @ColumnInfo(name = "img")
    val img: String? = null

)