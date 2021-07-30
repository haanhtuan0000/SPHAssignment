package com.haanhtuan.sphassignment.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity

import androidx.room.PrimaryKey


@Entity(tableName = "quarters")
class Quarter(
    @ColumnInfo(name = "volume_of_mobile_data")
    var volume_of_mobile_data: String? = null,
    @ColumnInfo(name = "quarter")
    var quarter: String? = null, var year: Int = 0,
    var quarterInYear: String? = null,
    var isDecreaseQuarter: Boolean = false
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

}