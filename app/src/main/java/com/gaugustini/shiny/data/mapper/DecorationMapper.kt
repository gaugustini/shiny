package com.gaugustini.shiny.data.mapper

import com.gaugustini.shiny.data.database.entity.DecorationEntity
import com.gaugustini.shiny.data.database.entity.DecorationSkillEntity
import com.gaugustini.shiny.domain.model.Decoration

object DecorationMapper {

    fun toDecorationList(
        decorationEntityList: List<DecorationEntity>,
        decorationSkillEntityList: List<DecorationSkillEntity>
    ): List<Decoration> {
        val decorationSkillMap = decorationSkillEntityList.groupBy { it.decorationId }

        return decorationEntityList.map { decoration ->
            val skillAndPointsMap = decorationSkillMap[decoration.id]
                .orEmpty()
                .associate { it.skillTreeId to it.pointValue }

            Decoration(
                id = decoration.id,
                requiredSlots = decoration.requiredSlots,
                skills = skillAndPointsMap
            )
        }
    }

}