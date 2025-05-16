package com.gaugustini.shiny.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.gaugustini.shiny.util.ArmorCategory
import com.gaugustini.shiny.util.Game
import com.gaugustini.shiny.util.Gender
import com.gaugustini.shiny.util.HunterType

@Entity(tableName = "armor", primaryKeys = ["armor_id"])
data class ArmorEntity(
    @ColumnInfo(name = "armor_id") val id: Int,
    @ColumnInfo(name = "game") val game: Game,
    @ColumnInfo(name = "category") val category: ArmorCategory,
    @ColumnInfo(name = "name") val name: Int,
    @ColumnInfo(name = "hr") val hunterRank: Int,
    @ColumnInfo(name = "village") val villageRank: Int,
    @ColumnInfo(name = "slots") val slots: Int,
    @ColumnInfo(name = "gender") val gender: Gender,
    @ColumnInfo(name = "type") val hunterType: HunterType,
    @ColumnInfo(name = "rarity") val rarity: Int,
    @ColumnInfo(name = "defense") val defense: Int,
    @ColumnInfo(name = "fire") val fire: Int,
    @ColumnInfo(name = "thunder") val thunder: Int,
    @ColumnInfo(name = "dragon") val dragon: Int,
    @ColumnInfo(name = "water") val water: Int,
    @ColumnInfo(name = "ice") val ice: Int,
    @ColumnInfo(name = "skill_one") val skillOne: Int?,
    @ColumnInfo(name = "skill_one_points") val skillOnePoints: Int?,
    @ColumnInfo(name = "skill_two") val skillTwo: Int?,
    @ColumnInfo(name = "skill_two_points") val skillTwoPoints: Int?,
    @ColumnInfo(name = "skill_three") val skillThree: Int?,
    @ColumnInfo(name = "skill_three_points") val skillThreePoints: Int?,
    @ColumnInfo(name = "skill_four") val skillFour: Int?,
    @ColumnInfo(name = "skill_four_points") val skillFourPoints: Int?,
    @ColumnInfo(name = "skill_five") val skillFive: Int?,
    @ColumnInfo(name = "skill_five_points") val skillFivePoints: Int?,
    @ColumnInfo(name = "material_one") val materialOne: Int?,
    @ColumnInfo(name = "amount_one") val amountOne: Int?,
    @ColumnInfo(name = "material_two") val materialTwo: Int?,
    @ColumnInfo(name = "amount_two") val amountTwo: Int?,
    @ColumnInfo(name = "material_three") val materialThree: Int?,
    @ColumnInfo(name = "amount_three") val amountThree: Int?,
    @ColumnInfo(name = "material_four") val materialFour: Int?,
    @ColumnInfo(name = "amount_four") val amountFour: Int?,
)