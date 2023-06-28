package com.mooooong.fastwash.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mooooong.fastwash.local.dao.ReserveDao
import com.mooooong.fastwash.local.dao.TokenDao
import com.mooooong.fastwash.local.entity.ReserveEntity
import com.mooooong.fastwash.local.entity.TokenEntity

@Database(
    entities = [ReserveEntity::class, TokenEntity::class],
    version = 2,
    exportSchema = false,
)
abstract class FastWashDatabase : RoomDatabase() {

    abstract fun reserveDao(): ReserveDao
    abstract fun tokenDao(): TokenDao

    companion object {
        private var instance: FastWashDatabase? = null

        @Synchronized
        fun getInstance(context: Context): FastWashDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    FastWashDatabase::class.java, "fastwash_database"
                )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
            }
            return instance
        }
    }
}