package com.gaugustini.shiny.repository

import com.gaugustini.shiny.data.ArmorSet
import com.gaugustini.shiny.data.Result
import com.gaugustini.shiny.data.toResult
import com.gaugustini.shiny.data.toResultEntity
import com.gaugustini.shiny.database.dao.ResultDao
import com.gaugustini.shiny.util.DataLanguage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResultRepositoryImpl @Inject constructor(private val resultDao: ResultDao) :
    ResultRepository {

    override suspend fun insertNewResults(results: List<ArmorSet>) {
        resultDao.clearAndInsertResults(results.map { it.toResultEntity() })
    }

    override suspend fun getResults(dataLanguage: DataLanguage): List<Result> {
        return resultDao.getResults(dataLanguage.code).map { it.toResult() }
    }

}