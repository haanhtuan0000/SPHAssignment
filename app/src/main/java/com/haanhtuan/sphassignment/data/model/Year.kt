package com.haanhtuan.sphassignment.data.model

class Year(
    var year: Int = 0,
    var dataConsume: String = "",
    var quarters: ArrayList<Quarter> = arrayListOf<Quarter>(),

    var isDecreasedQuarter: Boolean = false,
    var isShowQuartersDetail: Boolean = false
) {

}