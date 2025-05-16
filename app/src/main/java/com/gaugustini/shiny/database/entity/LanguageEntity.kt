package com.gaugustini.shiny.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "language", primaryKeys = ["language_id"])
data class LanguageEntity(
    @ColumnInfo(name = "language_id") val id: Int,
    @ColumnInfo(name = "name_id") val nameId: Int,
    @ColumnInfo(name = "language_code") val language: String,
    @ColumnInfo(name = "name") val name: String,
)