package com.gaugustini.shiny.domain.solution

import com.gaugustini.shiny.data.repository.ArmorRepository
import com.gaugustini.shiny.data.repository.DecorationRepository
import com.gaugustini.shiny.data.repository.ResultRepository
import com.gaugustini.shiny.domain.model.Armor
import com.gaugustini.shiny.domain.model.ArmorSet
import com.gaugustini.shiny.domain.model.Decoration
import com.gaugustini.shiny.domain.model.SearchOptions
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
    private lateinit var searchOptions: SearchOptions

    suspend fun start(userSearchOptions: SearchOptions): Boolean {
        searchOptions = userSearchOptions
        resultList.clear()
        getData()
        search()
        resultRepository.insertNewResults(resultList)
        return true
    }

    private suspend fun getData() {
        headList = armorRepository.getRelevantArmorList(0, searchOptions)
        bodyList = armorRepository.getRelevantArmorList(1, searchOptions)
        armsList = armorRepository.getRelevantArmorList(2, searchOptions)
        waistList = armorRepository.getRelevantArmorList(3, searchOptions)
        legsList = armorRepository.getRelevantArmorList(4, searchOptions)
        decorationList = decorationRepository.getRelevantDecorationList(searchOptions)
    }

    private fun search() {
        var idCounter = 0

        for (head in headList) {
            for (body in bodyList) {
                for (arms in armsList) {
                    for (waist in waistList) {
                        for (legs in legsList) {
                            armorSet = ArmorSet(
                                idCounter++,
                                searchOptions.weaponSlot,
                                head,
                                body,
                                arms,
                                waist,
                                legs
                            )

                            if (matchesSearchOptions()) {
                                resultList.add(armorSet)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun matchesSearchOptions(): Boolean {
        val skillAndMissingPoints = mutableMapOf<Int, Int>()

        searchOptions.skills.forEach { skill ->
            val currentPoints = armorSet.getSKillPoints(skill.skillTree)
            val missingPoints = skill.requiredPoints - currentPoints

            if (missingPoints > 0) {
                skillAndMissingPoints[skill.skillTree] = missingPoints
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

        searchOptions.skills.forEach { skill ->
            val currentPoints = armorSet.getSKillPoints(skill.skillTree)

            if (currentPoints < skill.requiredPoints) {
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
        var decorationsFiltered = decorations.filter { decoration -> decoration.requiredSlots == 3 }
        addDecorations(slotsAvailable[3], decorationsFiltered)

        // Remove amount of used slots, if not used then change to 2 and 1 slots
        armorSet.decorations.forEach { decoration -> slotsAvailable[decoration.requiredSlots] -= 1 }
        slotsAvailable[1] += slotsAvailable[3]
        slotsAvailable[2] += slotsAvailable[3]

        // Try adding Decorations with 2 slots
        decorationsFiltered = decorations.filter { decoration -> decoration.requiredSlots == 2 }
        addDecorations(slotsAvailable[2], decorationsFiltered)

        // Remove amount of used slots, if not used then change to 1 slots
        armorSet.decorations.forEach { decoration -> slotsAvailable[decoration.requiredSlots] -= 1 }
        slotsAvailable[1] += 2 * slotsAvailable[2]

        // Add Decorations with 1 slot
        decorationsFiltered = decorations.filter { decoration -> decoration.requiredSlots == 1 }
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
            val neededAmount =
                searchOptions.skills.find { it.skillTree == decorationSkill }!!.requiredPoints

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