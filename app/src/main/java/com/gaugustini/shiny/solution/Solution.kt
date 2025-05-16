package com.gaugustini.shiny.solution

import com.gaugustini.shiny.data.Armor
import com.gaugustini.shiny.data.ArmorSet
import com.gaugustini.shiny.data.Decoration
import com.gaugustini.shiny.data.Query
import com.gaugustini.shiny.repository.ArmorRepository
import com.gaugustini.shiny.repository.DecorationRepository
import com.gaugustini.shiny.repository.ResultRepository
import com.gaugustini.shiny.util.ArmorCategory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Solution @Inject constructor(
    private val armorRepository: ArmorRepository,
    private val decorationRepository: DecorationRepository,
    private val resultRepository: ResultRepository
) {

    private var headList = listOf<Armor>()
    private var bodyList = listOf<Armor>()
    private var armsList = listOf<Armor>()
    private var waistList = listOf<Armor>()
    private var legsList = listOf<Armor>()
    private var decorationList = listOf<Decoration>()
    private var resultList = mutableListOf<ArmorSet>()
    private lateinit var armorSet: ArmorSet
    private lateinit var query: Query

    suspend fun start(query: Query): Boolean {
        this@Solution.query = query
        resultList.clear()
        getData()
        search()
        resultRepository.insertNewResults(resultList)
        return true
    }

    private suspend fun getData() {
        headList = armorRepository.getRelevantArmorList(ArmorCategory.HEAD, query)
        bodyList = armorRepository.getRelevantArmorList(ArmorCategory.BODY, query)
        armsList = armorRepository.getRelevantArmorList(ArmorCategory.ARMS, query)
        waistList = armorRepository.getRelevantArmorList(ArmorCategory.WAIST, query)
        legsList = armorRepository.getRelevantArmorList(ArmorCategory.LEGS, query)
        decorationList = decorationRepository.getRelevantDecorationList(query)
    }

    private fun search() {

        for (head in headList) {
            for (body in bodyList) {
                for (arms in armsList) {
                    for (waist in waistList) {
                        for (legs in legsList) {
                            armorSet = ArmorSet(
                                query.weaponSlot,
                                head,
                                body,
                                arms,
                                waist,
                                legs
                            )

                            if (matchesQuery()) {
                                resultList.add(armorSet)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun matchesQuery(): Boolean {
        val skillAndMissingPoints = mutableMapOf<Int, Int>()

        query.skills.forEach { skill ->
            val currentPoints = armorSet.getSKillPoints(skill.familyID)
            val missingPoints = skill.points - currentPoints

            if (missingPoints > 0) {
                skillAndMissingPoints[skill.familyID] = missingPoints
            }
        }

        val decorationsForArmorSet = buildList {
            skillAndMissingPoints.forEach { (skill, points) ->
                val decorations = decorationList
                    .filter { decoration ->
                        decoration.skills.containsKey(skill)
                    }
                    .filter { decoration ->
                        decoration.skills[skill]!! in 1..points
                    }
                this.addAll(decorations)
            }
        }

        if (noDecorations(skillAndMissingPoints, decorationsForArmorSet)) {
            return false
        }

        calculateDecorations(decorationsForArmorSet)

        query.skills.forEach { skill ->
            val currentPoints = armorSet.getSKillPoints(skill.familyID)

            if (currentPoints < skill.points) {
                return false
            }
        }

        return true
    }

    private fun noDecorations(
        skillAndMissingPoints: MutableMap<Int, Int>,
        decorationsForArmorSet: List<Decoration>
    ): Boolean {
        skillAndMissingPoints.forEach { (skill, _) ->
            val decorations = decorationsForArmorSet.filter { decoration ->
                decoration.skills.containsKey(skill)
            }

            if (decorations.isEmpty()) {
                return true
            }
        }

        return false
    }

    private fun calculateDecorations(
        decorations: List<Decoration>
    ) {
        val slotsAvailable = armorSet.getSlotsAvailable().toMutableList()

        // Try adding Decorations with 3 slots
        var decorationsFiltered = decorations.filter { decoration -> decoration.slots == 3 }
        addDecorations(slotsAvailable[3], decorationsFiltered)

        // Remove amount of used slots, if not used then change to 2 and 1 slots
        armorSet.decorations.forEach { decoration -> slotsAvailable[decoration.slots] -= 1 }
        slotsAvailable[1] += slotsAvailable[3]
        slotsAvailable[2] += slotsAvailable[3]

        // Try adding Decorations with 2 slots
        decorationsFiltered = decorations.filter { decoration -> decoration.slots == 2 }
        addDecorations(slotsAvailable[2], decorationsFiltered)

        // Remove amount of used slots, if not used then change to 1 slots
        armorSet.decorations.forEach { decoration -> slotsAvailable[decoration.slots] -= 1 }
        slotsAvailable[1] += 2 * slotsAvailable[2]

        // Add Decorations with 1 slot
        decorationsFiltered = decorations.filter { decoration -> decoration.slots == 1 }
        addDecorations(slotsAvailable[1], decorationsFiltered)
    }

    private fun addDecorations(
        amountSlots: Int,
        decorations: List<Decoration>
    ) {
        if (amountSlots == 0 || decorations.isEmpty()) {
            return
        }

        var index = 0
        var slots = amountSlots

        while (true) {
            val decoration = decorations[index]
            val decorationSkill =
                decoration.skills.filter { (_, points) -> points > 0 }.firstNotNullOf { it.key }
            val decorationSkillPoints =
                decoration.skills.filter { (_, points) -> points > 0 }.firstNotNullOf { it.value }

            val currentAmount = armorSet.getSKillPoints(decorationSkill)
            val neededAmount = query.skills.find { it.familyID == decorationSkill }!!.points

            if (currentAmount + decorationSkillPoints <= neededAmount) {
                armorSet = armorSet.copy(decorations = armorSet.decorations + decoration)

                if (--slots == 0) {
                    break
                }

                index--
            }

            if (++index == decorations.size) {
                break
            }
        }
    }

}