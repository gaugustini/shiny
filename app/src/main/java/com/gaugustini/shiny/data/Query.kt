package com.gaugustini.shiny.data

import com.gaugustini.shiny.util.Game
import com.gaugustini.shiny.util.Gender
import com.gaugustini.shiny.util.HunterType

data class Query(
    var game: Game,
    var hunterRank: Int,
    var villageRank: Int,
    var weaponSlot: Int,
    var gender: Gender,
    var hunterType: HunterType,
    var skills: List<Skill>,
)