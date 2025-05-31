package com.gaugustini.shiny.data.mapper

import com.gaugustini.shiny.data.database.entity.SkillEntity
import com.gaugustini.shiny.domain.model.Skill

object SkillMapper {

    fun toSkillList(
        skillEntityList: List<SkillEntity>
    ): List<Skill> {
        return skillEntityList.map { skillEntity ->
            Skill(
                id = skillEntity.id,
                name = skillEntity.nameEn,
                skillTree = skillEntity.skillTreeId,
                requiredPoints = skillEntity.requiredSkillTreePoints
            )
        }
    }

}