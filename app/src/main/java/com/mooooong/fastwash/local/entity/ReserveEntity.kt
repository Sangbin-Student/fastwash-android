package com.mooooong.fastwash.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reserve_table")
data class ReserveEntity(
    @PrimaryKey val id: Int = 0,
    val time: String?,
)