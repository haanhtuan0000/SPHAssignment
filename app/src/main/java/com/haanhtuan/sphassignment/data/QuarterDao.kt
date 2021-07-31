package com.haanhtuan.sphassignment.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.haanhtuan.sphassignment.data.model.Quarter

@Dao
interface QuarterDao {
    @Query("SELECT * FROM quarters ORDER BY id DESC")
    suspend fun getAll(): List<Quarter>

    @Insert
    suspend fun insertAll(quarters: List<Quarter>)

    @Insert
    suspend fun insert(quarter: Quarter)

    @Query("DELETE FROM quarters")
    suspend fun nukeTable()
}