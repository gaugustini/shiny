package com.gaugustini.shiny.data.repository

import com.gaugustini.shiny.data.dao.ArmorDao
import com.gaugustini.shiny.data.mapper.toArmor
import com.gaugustini.shiny.domain.model.Armor
import com.gaugustini.shiny.domain.model.Query
import com.gaugustini.shiny.domain.repository.ArmorRepository
import com.gaugustini.shiny.util.ArmorCategory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArmorRepositoryImpl @Inject constructor(private val armorDao: ArmorDao) : ArmorRepository {

    override suspend fun getRelevantArmorList(
        category: ArmorCategory,
        query: Query,
        bestList: Boolean
    ): List<Armor> {
        var armorList = armorDao.getRelevantArmorList(
            query.game.value,
            category.value,
            query.hunterRank,
            query.villageRank,
            query.gender.value,
            query.hunterType.value,
            query.skills.map { it.familyID }
        ).map { it.toArmor() }

        if (bestList) {
            armorList = getBetterList(armorList, query.skills.map { it.familyID })
        }

        return armorList
    }

    private fun getBetterList(armorList: List<Armor>, skills: List<Int>): List<Armor> {
        val list = armorList.toMutableList()

        for (armorA in armorList) {
            for (armorB in armorList) {
                if (bestArmor(armorA, armorB, skills) &&
                    !bestArmor(armorB, armorA, skills) &&
                    list.indexOf(armorB) >= 0
                ) {
                    list.removeAt(list.indexOf(armorB))
                }
            }
        }

        return list
    }

    private fun bestArmor(armorA: Armor, armorB: Armor, skills: List<Int>): Boolean {
        if (armorA.slots > armorB.slots) {
            return true
        }

        skills.forEach { skill ->
            if ((armorA.skills[skill] ?: 0) > (armorB.skills[skill] ?: 0)) {
                return true
            }
        }

        return false
    }

}