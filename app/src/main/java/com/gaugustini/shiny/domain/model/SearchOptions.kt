package com.gaugustini.shiny.domain.model

data class SearchOptions(
    var game: Int,
    var hunterRank: Int,
    var villageRank: Int,
    var weaponSlot: Int,
    var gender: Int,
    var hunterType: Int,
    var skills: List<Skill>,
)