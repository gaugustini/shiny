package com.gaugustini.shiny.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.gaugustini.shiny.data.entity.DecorationEntity

@Dao
interface DecorationDao {

    @Query(
        """
        SELECT * FROM decoration WHERE
        game = :game AND
        (hr <= :hunterRank OR village <= :villageRank) AND
        skill_one IN (:skill)
        """
    )
    suspend fun getRelevantDecorationList(
        game: Int,
        hunterRank: Int,
        villageRank: Int,
        skill: List<Int>,
    ): List<DecorationEntity>

}