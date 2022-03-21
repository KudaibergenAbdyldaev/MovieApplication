package com.pacckage.data.local_db.model

import androidx.room.ColumnInfo

data class ResultsEntity(
    @ColumnInfo(name = "img")
    val img: String? = null,
    @ColumnInfo(name = "id")
    val id: Int? = null
)