package com.gaugustini.shiny.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

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
    @ColumnInfo(name = "dlc_event") val isDlcOrEvent: Boolean?,
    @ColumnInfo(name = "gender") val gender: Int,
    @ColumnInfo(name = "hunter_type") val hunterType: Int,
    @ColumnInfo(name = "rarity") val rarity: Int,
    @ColumnInfo(name = "num_slots") val numberOfSlots: Int,
    @ColumnInfo(name = "defense") val defense: Int,
    @ColumnInfo(name = "max_defense") val maxDefense: Int?,
    @ColumnInfo(name = "fire_res") val fireResistance: Int,
    @ColumnInfo(name = "water_res") val waterResistance: Int,
    @ColumnInfo(name = "thunder_res") val thunderResistance: Int,
    @ColumnInfo(name = "ice_res") val iceResistance: Int,
    @ColumnInfo(name = "dragon_res") val dragonResistance: Int,
    @ColumnInfo(name = "price") val price: Int?,
)

@Entity(
    tableName = "armor_skill",
    primaryKeys = ["armor_id", "skill_tree_id"],
    foreignKeys = [
        ForeignKey(
            entity = ArmorEntity::class,
            parentColumns = ["id"],
            childColumns = ["armor_id"],
        ),
        ForeignKey(
            entity = SkillTreeEntity::class,
            parentColumns = ["id"],
            childColumns = ["skill_tree_id"],
        )
    ],
    indices = [
        Index(value = ["skill_tree_id"])
    ]
)
data class ArmorSkillEntity(
    @ColumnInfo(name = "armor_id") val armorId: Int,
    @ColumnInfo(name = "skill_tree_id") val skillTreeId: Int,
    @ColumnInfo(name = "point_value") val pointValue: Int,
)

@Entity(
    tableName = "armor_recipe",
    primaryKeys = ["armor_id", "material_id"],
    foreignKeys = [
        ForeignKey(
            entity = ArmorEntity::class,
            parentColumns = ["id"],
            childColumns = ["armor_id"],
        ),
        ForeignKey(
            entity = MaterialEntity::class,
            parentColumns = ["id"],
            childColumns = ["material_id"],
        )
    ],
    indices = [
        Index(value = ["material_id"])
    ]
)
data class ArmorRecipeEntity(
    @ColumnInfo(name = "armor_id") val armorId: Int,
    @ColumnInfo(name = "material_id") val materialId: Int,
    @ColumnInfo(name = "quantity") val quantity: Int,
)
