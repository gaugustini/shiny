package com.gaugustini.shiny.domain.repository

import com.gaugustini.shiny.domain.model.Armor
import com.gaugustini.shiny.domain.model.Query
import com.gaugustini.shiny.util.ArmorCategory

interface ArmorRepository {

    suspend fun getRelevantArmorList(
        category: ArmorCategory,
        query: Query,
        bestList: Boolean = true
    ): List<Armor>

}