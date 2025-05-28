package com.gaugustini.shiny.repository

import com.gaugustini.shiny.data.Decoration
import com.gaugustini.shiny.data.Query
import com.gaugustini.shiny.data.toDecoration
import com.gaugustini.shiny.database.dao.DecorationDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DecorationRepository @Inject constructor(private val decorationDao: DecorationDao) {

    suspend fun getRelevantDecorationList(query: Query): List<Decoration> {
        return decorationDao.getRelevantDecorationList(
            query.game.value,
            query.hunterRank,
            query.villageRank,
        ).map { it.toDecoration() }
    }

}