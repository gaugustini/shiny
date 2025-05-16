package com.gaugustini.shiny.repository

import com.gaugustini.shiny.data.Armor
import com.gaugustini.shiny.data.Query
import com.gaugustini.shiny.util.ArmorCategory

interface ArmorRepository {

    suspend fun getRelevantArmorList(
        category: ArmorCategory,
        query: Query,
        bestList: Boolean = true
    ): List<Armor>

}