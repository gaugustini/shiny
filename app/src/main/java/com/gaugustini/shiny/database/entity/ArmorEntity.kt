package com.gaugustini.shiny.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "armor",
    primaryKeys = ["id"]
)
data class ArmorEntity(
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name_en") val nameEn: String,
    @ColumnInfo(name = "name_fr") val nameFr: String,
    @ColumnInfo(name = "name_de") val nameDe: String,
    @ColumnInfo(name = "name_es") val nameEs: String,
    @ColumnInfo(name = "name_it") val nameIt: String,
    @ColumnInfo(name = "name_jp") val nameJp: String?,
    @ColumnInfo(name = "game") val game: Int,
    @ColumnInfo(name = "armor_type") val armorType: Int,
    @ColumnInfo(name = "hunter_rank") val hunterRank: Int,
    @ColumnInfo(name = "village_stars") val villageStars: Int,
    @ColumnInfo(name = "dlc_event") val dlcEvent: Boolean?,
    @ColumnInfo(name = "gender") val gender: Int,
    @ColumnInfo(name = "hunter_type") val hunterType: Int,
    @ColumnInfo(name = "rarity") val rarity: Int,
    @ColumnInfo(name = "num_slots") val numSlots: Int,
    @ColumnInfo(name = "defense") val defense: Int,
    @ColumnInfo(name = "max_defense") val maxDefense: Int?,
    @ColumnInfo(name = "fire_res") val fireResistance: Int,
    @ColumnInfo(name = "water_res") val waterResistance: Int,
    @ColumnInfo(name = "thunder_res") val thunderResistance: Int,
    @ColumnInfo(name = "ice_res") val iceResistance: Int,
    @ColumnInfo(name = "dragon_res") val dragonResistance: Int,
    @ColumnInfo(name = "price") val price: Int?,
)