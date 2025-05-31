package com.gaugustini.shiny.presentation.search

import com.gaugustini.shiny.domain.model.Skill

data class SearchState(
    val villageRank: String = "9",
    val hunterRank: String = "9",
    val gender: String = "Male",
    val weaponSlot: String = "0",
    val hunterType: String = "Blademaster",
    val skills: List<Skill> = listOf(),
    val selectedSkills: List<Skill> = listOf(),

    val villageRankExpanded: Boolean = false,
    val hunterRankExpanded: Boolean = false,
    val genderExpanded: Boolean = false,
    val weaponSlotExpanded: Boolean = false,
    val selectedSkillsExpanded: Boolean = false,
    val hunterTypeExpanded: Boolean = false,

    val skillsSelectionIsOpen: Boolean = false,
    val skillsSearchQuery: String = "",
    val skillsFiltered: List<Skill> = skills,

    val isSearching: Boolean = false,
    val searchFinished: Boolean = false,
)