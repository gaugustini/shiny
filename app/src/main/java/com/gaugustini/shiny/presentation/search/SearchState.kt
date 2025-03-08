package com.gaugustini.shiny.presentation.search

data class SearchState(
    val villageRank: String = "9",
    val hunterRank: String = "9",
    val gender: String = "Male",
    val weaponSlot: String = "0",
    val hunterType: String = "Blademaster",
    val skills: List<String> = listOf("AAA", "ABC", "BBB", "CCC", "DDD", "EEE", "FFF", "GGG", "HHH", "III", "JJJ", "BIC", "KKK", "CDA", "DIA", "JKL", "MMM", "NNN", "NAM", "DOA", "FIA"),
    val selectedSkills: List<String> = listOf("AAA", "BBB", "CCC"),

    val skillsSelectionIsOpen: Boolean = false,
    val skillsSearchQuery: String = "",
    val skillsFiltered: List<String> = skills,

    val villageRankExpanded: Boolean = false,
    val hunterRankExpanded: Boolean = false,
    val genderExpanded: Boolean = false,
    val weaponSlotExpanded: Boolean = false,
    val selectedSkillsExpanded: Boolean = false,
    val hunterTypeExpanded: Boolean = false,

    val isSearching: Boolean = false,
    val searchFinished: Boolean = false,
)
