package com.gaugustini.shiny.data.repository

import com.gaugustini.shiny.data.database.dao.ResultDao
import com.gaugustini.shiny.data.mapper.ResultMapper
import com.gaugustini.shiny.domain.model.ArmorSet
import com.gaugustini.shiny.domain.model.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResultRepository @Inject constructor(private val resultDao: ResultDao) {

    suspend fun insertNewResults(results: List<ArmorSet>) {
        resultDao.insertNewResults(
            ResultMapper.toResultEntityList(results),
            ResultMapper.toResultDecorationEntityList(results)
        )
    }

    suspend fun getResultList(): List<Result> {
        return ResultMapper.toResultList(
            resultDao.getResultList(),
            resultDao.getResultDecorationList()
        )
    }

}