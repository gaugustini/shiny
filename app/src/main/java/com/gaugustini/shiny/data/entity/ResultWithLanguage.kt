package com.gaugustini.shiny.data.entity

import androidx.room.ColumnInfo

data class ResultWithLanguage(
    @ColumnInfo(name = "result_id") val id: String,
    @ColumnInfo(name = "head") val head: String,
    @ColumnInfo(name = "body") val body: String,
    @ColumnInfo(name = "arms") val arms: String,
    @ColumnInfo(name = "waist") val waist: String,
    @ColumnInfo(name = "legs") val legs: String,
    @ColumnInfo(name = "decoration_one") val decorationOne: String?,
    @ColumnInfo(name = "decoration_one_amount") val decorationOneAmount: Int?,
    @ColumnInfo(name = "decoration_two") val decorationTwo: String?,
    @ColumnInfo(name = "decoration_two_amount") val decorationTwoAmount: Int?,
    @ColumnInfo(name = "decoration_three") val decorationThree: String?,
    @ColumnInfo(name = "decoration_three_amount") val decorationThreeAmount: Int?,
    @ColumnInfo(name = "decoration_four") val decorationFour: String?,
    @ColumnInfo(name = "decoration_four_amount") val decorationFourAmount: Int?,
    @ColumnInfo(name = "decoration_five") val decorationFive: String?,
    @ColumnInfo(name = "decoration_five_amount") val decorationFiveAmount: Int?,
    @ColumnInfo(name = "decoration_six") val decorationSix: String?,
    @ColumnInfo(name = "decoration_six_amount") val decorationSixAmount: Int?,
    @ColumnInfo(name = "decoration_seven") val decorationSeven: String?,
    @ColumnInfo(name = "decoration_seven_amount") val decorationSevenAmount: Int?,
    @ColumnInfo(name = "decoration_eight") val decorationEight: String?,
    @ColumnInfo(name = "decoration_eight_amount") val decorationEightAmount: Int?,
    @ColumnInfo(name = "decoration_nine") val decorationNine: String?,
    @ColumnInfo(name = "decoration_nine_amount") val decorationNineAmount: Int?,
    @ColumnInfo(name = "decoration_ten") val decorationTen: String?,
    @ColumnInfo(name = "decoration_ten_amount") val decorationTenAmount: Int?
)