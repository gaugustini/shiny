package com.gaugustini.shiny.domain.repository

import com.gaugustini.shiny.domain.model.ArmorSet
import com.gaugustini.shiny.domain.model.Result
import com.gaugustini.shiny.util.DataLanguage

interface ResultRepository {

    suspend fun insertNewResults(results: List<ArmorSet>)

    suspend fun getResults(dataLanguage: DataLanguage): List<Result>

}