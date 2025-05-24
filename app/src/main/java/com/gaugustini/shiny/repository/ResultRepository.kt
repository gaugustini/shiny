package com.gaugustini.shiny.repository

import com.gaugustini.shiny.data.ArmorSet
import com.gaugustini.shiny.database.dao.ResultDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResultRepository @Inject constructor(private val resultDao: ResultDao) {

    suspend fun insertNewResults(results: List<ArmorSet>) {
        resultDao.clearResults()
    }

}