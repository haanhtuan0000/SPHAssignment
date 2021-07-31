package com.haanhtuan.sphassignment.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.haanhtuan.sphassignment.data.model.Quarter

@Database(entities = [Quarter::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun quarterDao(): QuarterDao
}