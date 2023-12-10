package com.gaugustini.shiny.data.repository

import com.gaugustini.shiny.data.dao.ResultDao
import com.gaugustini.shiny.data.mapper.toResult
import com.gaugustini.shiny.data.mapper.toResultEntity
import com.gaugustini.shiny.domain.model.ArmorSet
import com.gaugustini.shiny.domain.model.Result
import com.gaugustini.shiny.domain.repository.ResultRepository
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