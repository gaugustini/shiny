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
        category = :armorCategory AND
        (hr <= :hunterRank OR village <= :villageRank) AND
        gender IN (0, :gender) AND
        type IN (0, :hunterType) AND
        (skill_one IN (:skills) AND skill_one_points > 0 OR
        skill_two IN (:skills) AND skill_two_points > 0 OR
        skill_three IN (:skills) AND skill_three_points > 0 OR
        skill_four IN (:skills) AND skill_four_points > 0 OR
        skill_five IN (:skills) AND skill_five_points > 0)
        """
    )
    suspend fun getRelevantArmorList(
        game: Int,
        armorCategory: Int,
        hunterRank: Int,
        villageRank: Int,
        gender: Int,
        hunterType: Int,
        skills: List<Int>,
    ): List<ArmorEntity>

}