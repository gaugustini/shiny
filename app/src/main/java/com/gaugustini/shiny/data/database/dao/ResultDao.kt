package com.gaugustini.shiny.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.gaugustini.shiny.data.database.entity.ResultDecorationEntity
import com.gaugustini.shiny.data.database.entity.ResultEntity
import com.gaugustini.shiny.data.database.relation.ResultWithArmors
import com.gaugustini.shiny.data.database.relation.ResultWithDecoration

@Dao
interface ResultDao {

    @Query("DELETE FROM result")
    suspend fun deleteAllResult()

    @Query("DELETE FROM result_decoration")
    suspend fun deleteAllResultDecoration()

    @Insert
    suspend fun insertResultList(results: List<ResultEntity>)

    @Insert
    suspend fun insertResultDecorationList(resultDecorations: List<ResultDecorationEntity>)

    @Transaction
    suspend fun insertNewResults(
        results: List<ResultEntity>,
        resultDecorations: List<ResultDecorationEntity>
    ) {
        deleteAllResultDecoration()
        deleteAllResult()
        insertResultList(results)
        insertResultDecorationList(resultDecorations)
    }

    @Transaction
    @Query("SELECT * FROM result")
    suspend fun getResultList(): List<ResultWithArmors>

    @Transaction
    @Query("SELECT * FROM result_decoration")
    suspend fun getResultDecorationList(): List<ResultWithDecoration>

}