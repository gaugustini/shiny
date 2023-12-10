package com.gaugustini.shiny.data.entity

import androidx.room.ColumnInfo

data class SkillWithLanguage(
    @ColumnInfo(name = "skill_id") val skillID: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "family") val family: Int,
    @ColumnInfo(name = "points") val points: Int,
)