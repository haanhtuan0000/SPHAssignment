package com.haanhtuan.sphassignment.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.haanhtuan.sphassignment.data.model.Quarter

@Dao
interface QuarterDao {
    @Query("SELECT * FROM quarters ORDER BY id DESC")
    fun getAll(): List<Quarter>

    @Insert
    fun insertAll( quarters: List<Quarter>)

    @Insert
    fun insert( quarter: Quarter)
}