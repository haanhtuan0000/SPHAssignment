package com.haanhtuan.sphassignment

import com.haanhtuan.sphassignment.data.model.Quarter
import com.haanhtuan.sphassignment.data.model.Year
import timber.log.Timber
import java.lang.NumberFormatException


class Utils {
    fun convertDataForDisplay(quarterList: List<Quarter>): List<Year> {
        var years = transformDataToYear(quarterList)
        years = calculateUsageVolume(years)
        years = scanDecreaseQuarterInYear(years)

        return years
    }

    fun transformDataToYear(quarterList: List<Quarter>): List<Year> {
        val years = arrayListOf<Year>()
        var startYear = 0
        quarterList.filter { it.quarter != null }
            .map { transformToQuarterWithYear(it) }
            .filter { it.year in 2008..2018 }
            .sortedWith(compareBy({ it.year }, { it.quarter }))
            .forEach { quarter -> // form year list
                if (quarter.year == startYear && years.size > 0) {
                    years[years.size - 1].quarters.add(quarter)
                } else {
                    years.add(Year())
                    years[years.size - 1].quarters.add(quarter)
                    years[years.size - 1].year = quarter.year
                    startYear = quarter.year
                }
            }
        return years
    }

    fun calculateUsageVolume(years: List<Year>): List<Year> {
        var total = 0.0000
        years.forEach { quarter ->
            quarter.quarters.forEach {
                total += castFromStringToDouble(it.volume_of_mobile_data)
            }
            Timber.e("tuan: "+ String.format("%.4f", total))
            quarter.dataConsume = String.format("%.4f", total)
            total = 0.0000
        }

        return years
    }

    fun scanDecreaseQuarterInYear(years: List<Year>): List<Year> {
        years.forEach {
            it.quarters.forEachIndexed { index, _ ->
                if (index > 0) {
                    if (checkDecreaseQuarterVolume(
                            it.quarters[index],
                            it.quarters[index - 1]
                        )
                    ) {
                        it.isDecreasedQuarter = true
                        it.quarters[index].isDecreaseQuarter = true
                    }
                }
            }
        }

        return years
    }

    fun checkDecreaseQuarterVolume(quarter1: Quarter, quarter2: Quarter): Boolean {
        var volumeQuarter1 = castFromStringToDouble(quarter1.volume_of_mobile_data)
        var volumeQuarter2 = castFromStringToDouble(quarter2.volume_of_mobile_data)

        return volumeQuarter1 < volumeQuarter2
    }

    fun castFromStringToDouble(volume: String?): Double {
        if (volume == null) return 0.0000
        var volumeDouble = 0.0000
        try {
            volumeDouble = volume.toDouble()
        } catch (e: NumberFormatException) {

        }
        return volumeDouble

    }

    fun transformToQuarterWithYear(quarter: Quarter): Quarter {
        val yearQuarter = quarter.quarter?.split("-")?.toTypedArray()
        if (yearQuarter?.size == 2) {
            try {
                quarter.year = yearQuarter[0].toInt()
            } catch (e: NumberFormatException) {

            }
            quarter.quarterInYear = yearQuarter[1]
        }

        return quarter

    }

}