package com.gaugustini.shiny.repository

import com.gaugustini.shiny.data.Decoration
import com.gaugustini.shiny.data.Query

interface DecorationRepository {

    suspend fun getRelevantDecorationList(query: Query): List<Decoration>

}