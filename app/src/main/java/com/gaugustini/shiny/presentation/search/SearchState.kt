package com.gaugustini.shiny.presentation.search

import com.gaugustini.shiny.util.Gender
import com.gaugustini.shiny.util.HunterType

data class SearchState(
    val hunterRank: String = "1",
    val villageRank: String = "1",
    val weaponSlot: String = "0",
    val gender: Gender = Gender.MALE,
    val hunterType: HunterType = HunterType.BLADEMASTER,
    val skills: List<String> = listOf(),
    val querySkills: Set<String> = setOf()
)
