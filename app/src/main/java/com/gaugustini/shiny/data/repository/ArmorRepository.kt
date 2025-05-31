package com.gaugustini.shiny.data.repository

import com.gaugustini.shiny.data.database.dao.ArmorDao
import com.gaugustini.shiny.data.mapper.ArmorMapper
import com.gaugustini.shiny.domain.model.Armor
import com.gaugustini.shiny.domain.model.SearchOptions
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArmorRepository @Inject constructor(private val armorDao: ArmorDao) {

    suspend fun getRelevantArmorList(
        armorType: Int,
        searchOptions: SearchOptions,
    ): List<Armor> {
        val armorEntities = armorDao.getArmorList(
            searchOptions.game,
            armorType,
            searchOptions.hunterRank,
            searchOptions.villageRank,
            searchOptions.gender,
            searchOptions.hunterType,
            searchOptions.skills.map { it.skillTree }
        )
        val armorSkillEntities = armorDao.getArmorSkillList(armorEntities.map { it.id })

        var armorList = ArmorMapper.toArmorList(armorEntities, armorSkillEntities)
        armorList = getBetterList(armorList, searchOptions.skills.map { it.skillTree })

        return armorList
    }

    private fun getBetterList(armorList: List<Armor>, skills: List<Int>): List<Armor> {
        val remainingArmors = armorList.toMutableList()

        for (candidate in armorList) {
            for (competitor in armorList) {
                val candidateBetter = isBetter(candidate, competitor, skills)
                val competitorBetter = isBetter(competitor, candidate, skills)

                if (candidateBetter && !competitorBetter) {
                    remainingArmors.remove(competitor)
                }
            }
        }

        return remainingArmors
    }

    private fun isBetter(a: Armor, b: Armor, skills: List<Int>): Boolean {
        if (a.numberOfSlots > b.numberOfSlots) {
            return true
        }

        for (skill in skills) {
            val pointsA = a.skills[skill] ?: 0
            val pointsB = b.skills[skill] ?: 0
            if (pointsA > pointsB) {
                return true
            }
        }

        return false
    }

}