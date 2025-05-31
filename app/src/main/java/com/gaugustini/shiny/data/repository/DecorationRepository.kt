package com.gaugustini.shiny.data.repository

import com.gaugustini.shiny.data.database.dao.DecorationDao
import com.gaugustini.shiny.data.mapper.DecorationMapper
import com.gaugustini.shiny.domain.model.Decoration
import com.gaugustini.shiny.domain.model.SearchOptions
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DecorationRepository @Inject constructor(private val decorationDao: DecorationDao) {

    suspend fun getRelevantDecorationList(searchOptions: SearchOptions): List<Decoration> {
        val decorationEntities = decorationDao.getDecorationList(
            searchOptions.game,
            searchOptions.hunterRank,
            searchOptions.villageRank,
            searchOptions.skills.map { it.skillTree }
        )
        val decorationSkillEntities =
            decorationDao.getDecorationSkillList(decorationEntities.map { it.id })

        val decorationList =
            DecorationMapper.toDecorationList(decorationEntities, decorationSkillEntities)

        return decorationList
    }

}