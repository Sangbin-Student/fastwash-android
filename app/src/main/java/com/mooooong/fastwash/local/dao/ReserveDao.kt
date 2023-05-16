package com.mooooong.fastwash.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mooooong.fastwash.local.entity.ReserveEntity

@Dao
interface ReserveDao {

    @Query("SELECT * FROM reserve_table")
    suspend fun getIsReserve(): ReserveEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIsReserve(reserveEntity: ReserveEntity)
}