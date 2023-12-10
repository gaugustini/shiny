package com.gaugustini.shiny.data.repository

import com.gaugustini.shiny.data.dao.DecorationDao
import com.gaugustini.shiny.data.mapper.toDecoration
import com.gaugustini.shiny.domain.model.Decoration
import com.gaugustini.shiny.domain.model.Query
import com.gaugustini.shiny.domain.repository.DecorationRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DecorationRepositoryImpl @Inject constructor(private val decorationDao: DecorationDao) :
    DecorationRepository {

    override suspend fun getRelevantDecorationList(query: Query): List<Decoration> {
        return decorationDao.getRelevantDecorationList(
            query.game.value,
            query.hunterRank,
            query.villageRank,
            query.skills.map { it.familyID }
        ).map { it.toDecoration() }
    }

}