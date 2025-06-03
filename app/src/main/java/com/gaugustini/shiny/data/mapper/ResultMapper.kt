package com.gaugustini.shiny.data.mapper

import com.gaugustini.shiny.data.database.entity.ResultDecorationEntity
import com.gaugustini.shiny.data.database.entity.ResultEntity
import com.gaugustini.shiny.data.database.relation.ResultWithArmors
import com.gaugustini.shiny.data.database.relation.ResultWithDecoration
import com.gaugustini.shiny.domain.model.ArmorSet
import com.gaugustini.shiny.domain.model.Result

object ResultMapper {

    fun toResultList(
        results: List<ResultWithArmors>,
        resultDecorations: List<ResultWithDecoration>
    ): List<Result> {
        val resultDecorationMap = resultDecorations.groupBy { it.result.resultId }

        return results.map { result ->
            val decorationAndQuantityMap = resultDecorationMap[result.result.id]
                .orEmpty()
                .associate { it.decoration.nameEn to it.result.quantity }

            Result(
                head = result.head.nameEn,
                body = result.body.nameEn,
                arms = result.arms.nameEn,
                waist = result.waist.nameEn,
                legs = result.legs.nameEn,
                decorations = decorationAndQuantityMap
            )
        }
    }

    fun toResultEntityList(
        armorSets: List<ArmorSet>
    ): List<ResultEntity> {
        return armorSets.map { armorSet ->
            ResultEntity(
                id = armorSet.id,
                headId = armorSet.head.id,
                bodyId = armorSet.body.id,
                armsId = armorSet.arms.id,
                waistId = armorSet.waist.id,
                legsId = armorSet.legs.id,
            )
        }
    }

    fun toResultDecorationEntityList(
        armorSets: List<ArmorSet>
    ): List<ResultDecorationEntity> {
        val resultDecorationList = mutableListOf<ResultDecorationEntity>()

        for (armorSet in armorSets) {
            val decorationAndQuantityMap = armorSet.decorations.groupingBy { it }.eachCount()
            for (decoration in decorationAndQuantityMap) {
                resultDecorationList.add(
                    ResultDecorationEntity(
                        resultId = armorSet.id,
                        decorationId = decoration.key.id,
                        quantity = decoration.value
                    )
                )
            }
        }

        return resultDecorationList
    }

}