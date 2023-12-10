package com.gaugustini.shiny.domain.model

data class ArmorSet(
    val weapon: Int,
    val head: Armor,
    val body: Armor,
    val arms: Armor,
    val waist: Armor,
    val legs: Armor,
    val decorations: List<Decoration> = emptyList(),
) {

    fun getSKillPoints(familyID: Int): Int {
        var points = 0

        points += head.skills[familyID] ?: 0
        points += body.skills[familyID] ?: 0
        points += arms.skills[familyID] ?: 0
        points += waist.skills[familyID] ?: 0
        points += legs.skills[familyID] ?: 0

        decorations.forEach { decoration ->
            points += decoration.skills[familyID] ?: 0
        }

        return points
    }

    fun getSlotsAvailable(): List<Int> {
        val slots = mutableListOf(0, 0, 0, 0) // (0-slot, 1-slot, 2-slots, 3-slots)
        slots[weapon] += 1
        slots[head.slots] += 1
        slots[body.slots] += 1
        slots[arms.slots] += 1
        slots[waist.slots] += 1
        slots[legs.slots] += 1
        return slots
    }

}