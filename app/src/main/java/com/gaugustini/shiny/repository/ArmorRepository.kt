package com.gaugustini.shiny.repository

import com.gaugustini.shiny.data.Armor
import com.gaugustini.shiny.data.Query
import com.gaugustini.shiny.data.toArmor
import com.gaugustini.shiny.database.dao.ArmorDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArmorRepository @Inject constructor(private val armorDao: ArmorDao) {

    suspend fun getRelevantArmorList(
        category: Int,
        query: Query,
        bestList: Boolean = true
    ): List<Armor> {
        var armorList = armorDao.getRelevantArmorList(
            query.game.value,
            category,
            query.hunterRank,
            query.villageRank,
            query.gender.value,
            query.hunterType.value,
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