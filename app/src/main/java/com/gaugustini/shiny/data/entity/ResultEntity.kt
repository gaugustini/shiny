package com.gaugustini.shiny.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "result")
data class ResultEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "result_id") val id: Int = 0,
    @ColumnInfo(name = "head") val head: Int,
    @ColumnInfo(name = "body") val body: Int,
    @ColumnInfo(name = "arms") val arms: Int,
    @ColumnInfo(name = "waist") val waist: Int,
    @ColumnInfo(name = "legs") val legs: Int,
    @ColumnInfo(name = "decoration_one") val decorationOne: Int?,
    @ColumnInfo(name = "decoration_one_amount") val decorationOneAmount: Int?,
    @ColumnInfo(name = "decoration_two") val decorationTwo: Int?,
    @ColumnInfo(name = "decoration_two_amount") val decorationTwoAmount: Int?,
    @ColumnInfo(name = "decoration_three") val decorationThree: Int?,
    @ColumnInfo(name = "decoration_three_amount") val decorationThreeAmount: Int?,
    @ColumnInfo(name = "decoration_four") val decorationFour: Int?,
    @ColumnInfo(name = "decoration_four_amount") val decorationFourAmount: Int?,
    @ColumnInfo(name = "decoration_five") val decorationFive: Int?,
    @ColumnInfo(name = "decoration_five_amount") val decorationFiveAmount: Int?,
    @ColumnInfo(name = "decoration_six") val decorationSix: Int?,
    @ColumnInfo(name = "decoration_six_amount") val decorationSixAmount: Int?,
    @ColumnInfo(name = "decoration_seven") val decorationSeven: Int?,
    @ColumnInfo(name = "decoration_seven_amount") val decorationSevenAmount: Int?,
    @ColumnInfo(name = "decoration_eight") val decorationEight: Int?,
    @ColumnInfo(name = "decoration_eight_amount") val decorationEightAmount: Int?,
    @ColumnInfo(name = "decoration_nine") val decorationNine: Int?,
    @ColumnInfo(name = "decoration_nine_amount") val decorationNineAmount: Int?,
    @ColumnInfo(name = "decoration_ten") val decorationTen: Int?,
    @ColumnInfo(name = "decoration_ten_amount") val decorationTenAmount: Int?
)