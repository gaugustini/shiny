package com.gaugustini.shiny.data.mapper

import com.gaugustini.shiny.data.database.entity.ArmorEntity
import com.gaugustini.shiny.data.database.entity.ArmorSkillEntity
import com.gaugustini.shiny.domain.model.Armor

object ArmorMapper {

    fun toArmorList(
        armorEntityList: List<ArmorEntity>,
        armorSkillEntityList: List<ArmorSkillEntity>
    ): List<Armor> {
        val armorSkillMap = armorSkillEntityList.groupBy { it.armorId }

        return armorEntityList.map { armor ->
            val skillAndPointsMap = armorSkillMap[armor.id]
                .orEmpty()
                .associate { it.skillTreeId to it.pointValue }

            Armor(
                id = armor.id,
                category = armor.armorType,
                numberOfSlots = armor.numberOfSlots,
                skills = skillAndPointsMap
            )
        }
    }

}