package com.haanhtuan.sphassignment.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity

import androidx.room.PrimaryKey


@Entity(tableName = "quarters")
class Quarter {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "volume")
    var volume: String? = null

    @ColumnInfo(name = "quarter")
    var quarter: String? = null

    var isDecreasedQuarter: Boolean = false
}