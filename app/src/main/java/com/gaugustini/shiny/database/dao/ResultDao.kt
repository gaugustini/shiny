package com.gaugustini.shiny.database.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ResultDao {

    @Query("DELETE FROM result")
    suspend fun clearResults()

}