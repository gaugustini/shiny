package com.gaugustini.shiny.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "skill",
    primaryKeys = ["id"],
    foreignKeys = [
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
data class SkillEntity(
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name_en") val nameEn: String,
    @ColumnInfo(name = "name_fr") val nameFr: String,
    @ColumnInfo(name = "name_de") val nameDe: String,
    @ColumnInfo(name = "name_es") val nameEs: String,
    @ColumnInfo(name = "name_it") val nameIt: String,
    @ColumnInfo(name = "name_jp") val nameJp: String?,
    @ColumnInfo(name = "game") val game: Int,
    @ColumnInfo(name = "skill_tree_id") val skillTreeId: Int,
    @ColumnInfo(name = "required_skill_tree_points") val requiredSkillTreePoints: Int,
    @ColumnInfo(name = "hunter_type") val hunterType: Int,
    @ColumnInfo(name = "skill_category") val skillCategory: String?,
)