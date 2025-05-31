package com.gaugustini.shiny.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.gaugustini.shiny.data.database.entity.SkillEntity

@Dao
interface SkillDao {

    @Query(
        """
        SELECT * FROM skill
        WHERE 
        game = :game AND
        hunter_type IN (0, :hunterType) AND
        required_skill_tree_points >= 10 
        ORDER BY skill_tree_id, required_skill_tree_points
        """
    )
    suspend fun getSkillList(
        game: Int,
        hunterType: Int,
    ): List<SkillEntity>

}