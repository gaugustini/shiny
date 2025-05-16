package com.gaugustini.shiny.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.gaugustini.shiny.util.Game

@Entity(tableName = "decoration", primaryKeys = ["decoration_id"])
data class DecorationEntity(
    @ColumnInfo(name = "decoration_id") val id: Int,
    @ColumnInfo(name = "game") val game: Game,
    @ColumnInfo(name = "name") val name: Int,
    @ColumnInfo(name = "hr") val hunterRank: Int,
    @ColumnInfo(name = "village") val villageRank: Int,
    @ColumnInfo(name = "slots") val slots: Int,
    @ColumnInfo(name = "skill_one") val skillOne: Int,
    @ColumnInfo(name = "skill_one_points") val skillOnePoints: Int,
    @ColumnInfo(name = "skill_two") val skillTwo: Int?,
    @ColumnInfo(name = "skill_two_points") val skillTwoPoints: Int?,
    @ColumnInfo(name = "material_one") val materialOne: Int?,
    @ColumnInfo(name = "amount_one") val amountOne: Int?,
    @ColumnInfo(name = "material_two") val materialTwo: Int?,
    @ColumnInfo(name = "amount_two") val amountTwo: Int?,
    @ColumnInfo(name = "material_three") val materialThree: Int?,
    @ColumnInfo(name = "amount_three") val amountThree: Int?,
    @ColumnInfo(name = "material_four") val materialFour: Int?,
    @ColumnInfo(name = "amount_four") val amountFour: Int?,
    @ColumnInfo(name = "alt_material_one") val alternativeMaterialOne: Int?,
    @ColumnInfo(name = "alt_amount_one") val alternativeAmountOne: Int?,
    @ColumnInfo(name = "alt_material_two") val alternativeMaterialTwo: Int?,
    @ColumnInfo(name = "alt_amount_two") val alternativeAmountTwo: Int?,
    @ColumnInfo(name = "alt_material_three") val alternativeMaterialThree: Int?,
    @ColumnInfo(name = "alt_amount_three") val alternativeAmountThree: Int?,
    @ColumnInfo(name = "alt_material_four") val alternativeMaterialFour: Int?,
    @ColumnInfo(name = "alt_amount_four") val alternativeAmountFour: Int?,
)