package com.gaugustini.shiny.domain.repository

import com.gaugustini.shiny.domain.model.Decoration
import com.gaugustini.shiny.domain.model.Query

interface DecorationRepository {

    suspend fun getRelevantDecorationList(query: Query): List<Decoration>

}