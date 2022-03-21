package com.pacckage.data.local_db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remoteKey")
data class RemoteKeys(
    @PrimaryKey
    val repoId:Int,
    val prevKey:Int?,
    val nextKey:Int?
)
