package com.gaugustini.shiny.presentation.search

import com.gaugustini.shiny.domain.model.Skill
import com.gaugustini.shiny.util.DataLanguage
import com.gaugustini.shiny.util.Game
import com.gaugustini.shiny.util.Gender
import com.gaugustini.shiny.util.HunterType

data class SearchState(
    val game: Game = Game.MHFU,
    val dataLanguage: DataLanguage = DataLanguage.ENGLISH,
    val hunterRank: String = "9",
    val villageRank: String = "9",
    val weaponSlot: String = "0",
    val gender: Gender = Gender.MALE,
    val hunterType: HunterType = HunterType.BLADEMASTER,
    val skills: List<Skill> = listOf(),
    val querySkills: Set<Skill> = setOf(
        Skill(id = 237, name = "Sharpness +1", familyID = 4172, points = 10),
        Skill(id = 248, name = "Art of Unsheathing", familyID = 4178, points = 10),
        Skill(id = 303, name = "Focus", familyID = 4209, points = 10),
        Skill(id = 358, name = "Item Usage Improve", familyID = 4232, points = 10)
    ),
    val isSearching: Boolean = false,
    val searchFinished: Boolean = false,
)
