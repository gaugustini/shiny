package com.gaugustini.shiny.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

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

@Entity(
    tableName = "decoration_skill",
    primaryKeys = ["decoration_id", "skill_tree_id"],
    foreignKeys = [
        ForeignKey(
            entity = DecorationEntity::class,
            parentColumns = ["id"],
            childColumns = ["decoration_id"],
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
data class DecorationSkillEntity(
    @ColumnInfo(name = "decoration_id") val decorationId: Int,
    @ColumnInfo(name = "skill_tree_id") val skillTreeId: Int,
    @ColumnInfo(name = "point_value") val pointValue: Int,
)

@Entity(
    tableName = "decoration_recipe",
    primaryKeys = ["decoration_id", "material_id", "recipe_variant"],
    foreignKeys = [
        ForeignKey(
            entity = DecorationEntity::class,
            parentColumns = ["id"],
            childColumns = ["decoration_id"],
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
data class DecorationRecipeEntity(
    @ColumnInfo(name = "decoration_id") val decorationId: Int,
    @ColumnInfo(name = "material_id") val materialId: Int,
    @ColumnInfo(name = "recipe_variant") val recipeVariant: Int,
    @ColumnInfo(name = "quantity") val quantity: Int,
)
