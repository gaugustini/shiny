package com.gaugustini.shiny.data

import com.gaugustini.shiny.database.entity.ArmorEntity
import com.gaugustini.shiny.database.entity.DecorationEntity

fun ArmorEntity.toArmor(): Armor {
    val skillsMap = mutableMapOf<Int, Int>()
    return Armor(
        nameID = id,
        category = armorType,
        slots = numberOfSlots,
        skills = skillsMap
    )
}

fun DecorationEntity.toDecoration(): Decoration {
    val skillsMap = mutableMapOf<Int, Int>()
    return Decoration(
        nameID = id,
        slots = requiredSlots,
        skills = skillsMap
    )
}
