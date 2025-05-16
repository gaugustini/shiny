package com.gaugustini.shiny.repository

import com.gaugustini.shiny.data.ArmorSet
import com.gaugustini.shiny.data.Result
import com.gaugustini.shiny.util.DataLanguage

interface ResultRepository {

    suspend fun insertNewResults(results: List<ArmorSet>)

    suspend fun getResults(dataLanguage: DataLanguage): List<Result>

}