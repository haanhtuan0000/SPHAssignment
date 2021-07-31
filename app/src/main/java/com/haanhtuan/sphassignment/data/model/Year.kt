package com.haanhtuan.sphassignment.data.model

class Year(
    var year: Int = 0,
    var dataConsume: String = "",
    var quarters: ArrayList<Quarter> = arrayListOf<Quarter>(),

    var isDecreasedQuarter: Boolean = false,
    var isShowQuartersDetail: Boolean = false
) {
    fun yearToString(): String {
        return year.toString()
    }

    fun isQ1Decrease(): Boolean {
        return quarters.size> 0 && quarters[0].isDecreaseQuarter
    }
    fun isQ2Decrease(): Boolean {
        return quarters.size> 0 && quarters[1].isDecreaseQuarter
    }
    fun isQ3Decrease(): Boolean {
        return quarters.size> 0 && quarters[2].isDecreaseQuarter
    }
    fun isQ4Decrease(): Boolean {
        return quarters.size> 0 && quarters[3].isDecreaseQuarter
    }

}