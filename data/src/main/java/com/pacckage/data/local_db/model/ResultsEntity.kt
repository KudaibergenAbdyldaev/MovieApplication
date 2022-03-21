package com.pacckage.data.local_db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular_movie")
data class ResultsEntity(
    @PrimaryKey
    @ColumnInfo(name = "movieId")
    val id: Int,
    @ColumnInfo(name = "img")
    val img: String? = null

)