package com.gaugustini.shiny.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "decoration",
    primaryKeys = ["id"]
)
data class DecorationEntity(
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name_en") val nameEn: String,
    @ColumnInfo(name = "name_fr") val nameFr: String,
    @ColumnInfo(name = "name_de") val nameDe: String,
    @ColumnInfo(name = "name_es") val nameEs: String,
    @ColumnInfo(name = "name_it") val nameIt: String,
    @ColumnInfo(name = "name_jp") val nameJp: String?,
    @ColumnInfo(name = "game") val game: Int,
    @ColumnInfo(name = "hunter_rank") val hunterRank: Int,
    @ColumnInfo(name = "village_stars") val villageStars: Int,
    @ColumnInfo(name = "required_slots") val requiredSlots: Int,
    @ColumnInfo(name = "price") val price: Int?,
)