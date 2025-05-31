package com.gaugustini.shiny.data.repository

import com.gaugustini.shiny.data.database.dao.SkillDao
import com.gaugustini.shiny.data.mapper.SkillMapper
import com.gaugustini.shiny.domain.model.Skill
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SkillRepository @Inject constructor(private val skillDao: SkillDao) {

    suspend fun getSkillList(hunterType: Int, game: Int = 0): List<Skill> {
        val skillList = skillDao.getSkillList(game, hunterType)
        return SkillMapper.toSkillList(skillList)
    }

}