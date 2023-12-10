package com.gaugustini.shiny.domain.repository

import com.gaugustini.shiny.domain.model.Skill
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