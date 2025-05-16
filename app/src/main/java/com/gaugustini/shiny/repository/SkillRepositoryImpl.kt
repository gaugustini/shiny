package com.gaugustini.shiny.repository

import com.gaugustini.shiny.data.Skill
import com.gaugustini.shiny.data.toSkill
import com.gaugustini.shiny.database.dao.SkillDao
import com.gaugustini.shiny.util.DataLanguage
import com.gaugustini.shiny.util.Game
import com.gaugustini.shiny.util.HunterType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SkillRepositoryImpl @Inject constructor(private val skillDao: SkillDao) : SkillRepository {
    override suspend fun getSkillList(
        game: Game,
        dataLanguage: DataLanguage,
        hunterType: HunterType
    ): List<Skill> {
        val skills =
            skillDao.getSkillWithLanguageList(game.value, dataLanguage.code, hunterType.value)
        return skills.map { it.toSkill() }
    }

}