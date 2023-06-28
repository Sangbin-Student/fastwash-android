package com.mooooong.fastwash.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mooooong.fastwash.local.entity.TokenEntity

@Dao
interface TokenDao {

    @Query("SELECT * FROM token_table WHERE idx = 0")
    suspend fun getToken(): TokenEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: TokenEntity)

    @Query("DELETE FROM token_table")
    suspend fun deleteToken()
}