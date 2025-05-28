package com.gaugustini.shiny.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "material",
    primaryKeys = ["id"]
)
data class MaterialEntity(
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name_en") val nameEn: String,
    @ColumnInfo(name = "name_fr") val nameFr: String,
    @ColumnInfo(name = "name_de") val nameDe: String,
    @ColumnInfo(name = "name_es") val nameEs: String,
    @ColumnInfo(name = "name_it") val nameIt: String,
    @ColumnInfo(name = "name_jp") val nameJp: String?,
)