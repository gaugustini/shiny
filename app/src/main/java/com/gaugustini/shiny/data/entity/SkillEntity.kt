package com.gaugustini.shiny.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.gaugustini.shiny.util.Game
import com.gaugustini.shiny.util.HunterType

@Entity(tableName = "skill", primaryKeys = ["skill_id"])
data class SkillEntity(
    @ColumnInfo(name = "skill_id") val id: Int,
    @ColumnInfo(name = "game") val game: Game,
    @ColumnInfo(name = "name") val name: Int,
    @ColumnInfo(name = "family") val family: Int, // Skill Tree, e.g. Atk. Up (Large) belongs to family Atk.
    @ColumnInfo(name = "points") val points: Int,
    @ColumnInfo(name = "type") val hunterType: HunterType,
)