package com.haanhtuan.sphassignment

import com.google.gson.GsonBuilder
import com.haanhtuan.sphassignment.data.model.Quarter
import com.haanhtuan.sphassignment.data.model.Year
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before

import org.junit.Test

class UtilsTest {
    private val utils= Utils()

    @Before
    fun setUp() {

    }

    private fun createQuarterList(): List<Quarter> {
        val quarters = arrayListOf<Quarter>()
        quarters.add(Quarter(volume_of_mobile_data = "10.2324", quarter = null))
        quarters.add(Quarter(volume_of_mobile_data = "10.2324", quarter = "2017-Q2"))
        quarters.add(Quarter(volume_of_mobile_data = "11.2324", quarter = "2017-Q3"))
        quarters.add(Quarter(volume_of_mobile_data = "1.2324", quarter = "2017-Q4"))
        quarters.add(Quarter(volume_of_mobile_data = "8.2324", quarter = "2015-Q2"))
        quarters.add(Quarter(volume_of_mobile_data = "19.2624", quarter = "2016-Q1"))
        quarters.add(Quarter(volume_of_mobile_data = "10.2424", quarter = "2016-Q4"))
        quarters.add(Quarter(volume_of_mobile_data = "20.2324", quarter = "2019-Q4"))
        quarters.add(Quarter(volume_of_mobile_data = "20.2324", quarter = "2001-Q4"))
        return quarters
    }

    private fun prepareExpectedYearList_transformDataToYearTest(): List<Year> {
        val yearResult = arrayListOf<Year>()
        var quarterList = arrayListOf<Quarter>()

        quarterList.add(Quarter(volume_of_mobile_data = "8.2324",quarter = "2015-Q2",year = 2015, quarterInYear = "Q2"))
        yearResult.add(Year(year = 2015, quarters = quarterList))

        quarterList = arrayListOf()
        quarterList.add(Quarter(volume_of_mobile_data = "19.2624",quarter = "2016-Q1",year = 2016, quarterInYear = "Q1"))
        quarterList.add(Quarter(volume_of_mobile_data = "10.2424",quarter = "2016-Q4",year = 2016, quarterInYear = "Q4"))
        yearResult.add(Year(year = 2016, quarters = quarterList))

        quarterList = arrayListOf()
        quarterList.add(Quarter(volume_of_mobile_data = "10.2324",quarter = "2017-Q2",year = 2017, quarterInYear = "Q2"))
        quarterList.add(Quarter(volume_of_mobile_data = "11.2324",quarter = "2017-Q3",year = 2017, quarterInYear = "Q3"))
        quarterList.add(Quarter(volume_of_mobile_data = "1.2324",quarter = "2017-Q4",year = 2017, quarterInYear = "Q4"))
        yearResult.add(Year(year = 2017,quarters = quarterList))

        return yearResult
    }

    private fun prepareExpectedYearList_calculateUsageVolume(): List<Year> {
        val yearResult = arrayListOf<Year>()
        var quarterList = arrayListOf<Quarter>()

        quarterList.add(Quarter(volume_of_mobile_data = "8.2324",quarter = "2015-Q2",year = 2015, quarterInYear = "Q2"))
        yearResult.add(Year(year = 2015, dataConsume = "8.2324", quarters = quarterList))

        quarterList = arrayListOf()
        quarterList.add(Quarter(volume_of_mobile_data = "19.2624",quarter = "2016-Q1",year = 2016, quarterInYear = "Q1"))
        quarterList.add(Quarter(volume_of_mobile_data = "10.2424",quarter = "2016-Q4",year = 2016, quarterInYear = "Q4"))
        yearResult.add(Year(year = 2016, dataConsume = "29.5048", quarters = quarterList))

        quarterList = arrayListOf()
        quarterList.add(Quarter(volume_of_mobile_data = "10.2324",quarter = "2017-Q2",year = 2017, quarterInYear = "Q2"))
        quarterList.add(Quarter(volume_of_mobile_data = "11.2324",quarter = "2017-Q3",year = 2017, quarterInYear = "Q3"))
        quarterList.add(Quarter(volume_of_mobile_data = "1.2324",quarter = "2017-Q4",year = 2017, quarterInYear = "Q4"))
        yearResult.add(Year(year = 2017,dataConsume = "22.6972",quarters = quarterList))

        return yearResult
    }

    private fun prepareExpectedYearList_scanDecreaseQuarterInYear(): List<Year> {
        val yearResult = arrayListOf<Year>()
        var quarterList = arrayListOf<Quarter>()

        quarterList.add(Quarter(volume_of_mobile_data = "8.2324",quarter = "2015-Q2",year = 2015, quarterInYear = "Q2"))
        yearResult.add(Year(year = 2015, dataConsume = "8.2324", quarters = quarterList, isDecreasedQuarter = false))

        quarterList = arrayListOf()
        quarterList.add(Quarter(volume_of_mobile_data = "19.2624",quarter = "2016-Q1",year = 2016, quarterInYear = "Q1"))
        quarterList.add(Quarter(volume_of_mobile_data = "10.2424",quarter = "2016-Q4",year = 2016, quarterInYear = "Q4", isDecreaseQuarter = true))
        yearResult.add(Year(year = 2016, dataConsume = "29.5048", quarters = quarterList, isDecreasedQuarter = true))

        quarterList = arrayListOf()
        quarterList.add(Quarter(volume_of_mobile_data = "10.2324",quarter = "2017-Q2",year = 2017, quarterInYear = "Q2"))
        quarterList.add(Quarter(volume_of_mobile_data = "11.2324",quarter = "2017-Q3",year = 2017, quarterInYear = "Q3"))
        quarterList.add(Quarter(volume_of_mobile_data = "1.2324",quarter = "2017-Q4",year = 2017, quarterInYear = "Q4", isDecreaseQuarter = true))
        yearResult.add(Year(year = 2017,dataConsume = "22.6972",quarters = quarterList, isDecreasedQuarter = true))

        return yearResult
    }



    @Test
    fun calculateUsageVolumeTest(){
        val result = utils.calculateUsageVolume(prepareExpectedYearList_transformDataToYearTest())
        val expected = prepareExpectedYearList_calculateUsageVolume()

        val strResult = GsonBuilder().serializeNulls().create().toJson(result)
        val strExpected = GsonBuilder().serializeNulls().create().toJson(expected)

        assertEquals(strExpected, strResult)
    }

    @Test
    fun scanDecreaseQuarterInYearTest(){
        val result = utils.scanDecreaseQuarterInYear(prepareExpectedYearList_calculateUsageVolume())
        val expected = prepareExpectedYearList_scanDecreaseQuarterInYear()

        val strResult = GsonBuilder().serializeNulls().create().toJson(result)
        val strExpected = GsonBuilder().serializeNulls().create().toJson(expected)

        assertEquals(strExpected, strResult)
    }

    @Test
    fun checkDecreaseQuarterVolumeTest(){
        val quarter1 = Quarter(volume_of_mobile_data = "19.2624",quarter = "2016-Q1",year = 2016, quarterInYear = "Q1")
        val quarter2 = Quarter(volume_of_mobile_data = "10.2424",quarter = "2016-Q4",year = 2016, quarterInYear = "Q4", isDecreaseQuarter = true)


        assertEquals(true, utils.checkDecreaseQuarterVolume(quarter2, quarter1))
        assertEquals(false, utils.checkDecreaseQuarterVolume(quarter1, quarter2))
    }

    @Test
    fun castFromStringToDoubleTest(){
        assertEquals(0.0, utils.castFromStringToDouble(null), 0.0005)
        assertEquals(10.1234, utils.castFromStringToDouble("10.1234"),0.0005)
        assertEquals(0.0, utils.castFromStringToDouble(""),0.0005)
    }

    @Test
    fun transformToQuarterWithYearTest(){
        val quarter1 = Quarter(volume_of_mobile_data = "19.2624",quarter = "2016-Q1")
        val quarter2 = Quarter(volume_of_mobile_data = "19.2624",quarter = "2016-Q1",year = 2016, quarterInYear = "Q1")

        val strResult = GsonBuilder().serializeNulls().create().toJson(utils.transformToQuarterWithYear(quarter1))
        val strExpected = GsonBuilder().serializeNulls().create().toJson(quarter2)

        assertEquals(strExpected, strResult)
    }

    @Test
    fun transformDataToYearTest() {
        val result = utils.transformDataToYear(createQuarterList())
        val expected = prepareExpectedYearList_transformDataToYearTest()

        val strResult = GsonBuilder().serializeNulls().create().toJson(result)
        val strExpected = GsonBuilder().serializeNulls().create().toJson(expected)

        assertEquals(strExpected, strResult)
    }
}