package com.gaugustini.shiny.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.gaugustini.shiny.database.entity.DecorationEntity

@Dao
interface DecorationDao {

    @Query(
        """
        SELECT * FROM decoration WHERE
        game = :game AND
        (hunter_rank <= :hunterRank OR village_stars <= :villageStars)
        """
    )
    suspend fun getRelevantDecorationList(
        game: Int,
        hunterRank: Int,
        villageStars: Int,
    ): List<DecorationEntity>

}