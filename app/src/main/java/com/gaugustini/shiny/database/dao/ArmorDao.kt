package com.gaugustini.shiny.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.gaugustini.shiny.database.entity.ArmorEntity

@Dao
interface ArmorDao {

    @Query(
        """
        SELECT * FROM armor WHERE
        game = :game AND
        armor_type = :armorType AND
        (hunter_rank <= :hunterRank OR village_stars <= :villageStars) AND
        gender IN (0, :gender) AND
        hunter_type IN (0, :hunterType)
        """
    )
    suspend fun getRelevantArmorList(
        game: Int,
        armorType: Int,
        hunterRank: Int,
        villageStars: Int,
        gender: Int,
        hunterType: Int,
    ): List<ArmorEntity>

}