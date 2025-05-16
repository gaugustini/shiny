package com.gaugustini.shiny.repository

import com.gaugustini.shiny.data.Skill
import com.gaugustini.shiny.util.DataLanguage
import com.gaugustini.shiny.util.Game
import com.gaugustini.shiny.util.HunterType

interface SkillRepository {

    suspend fun getSkillList(
        game: Game,
        dataLanguage: DataLanguage,
        hunterType: HunterType
    ): List<Skill>

}