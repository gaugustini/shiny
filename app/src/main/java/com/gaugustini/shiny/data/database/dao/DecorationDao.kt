package com.gaugustini.shiny.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.gaugustini.shiny.data.database.entity.DecorationEntity
import com.gaugustini.shiny.data.database.entity.DecorationSkillEntity

@Dao
interface DecorationDao {

    @Query(
        """
        SELECT DISTINCT decoration.* FROM decoration
        JOIN decoration_skill ON decoration_skill.decoration_id = decoration.id
        WHERE
        decoration.game = :game AND
        (decoration.hunter_rank <= :hunterRank OR decoration.village_stars <= :villageStars) AND
        decoration_skill.skill_tree_id IN (:skills) AND
        decoration_skill.point_value > 0
        """
    )
    suspend fun getDecorationList(
        game: Int,
        hunterRank: Int,
        villageStars: Int,
        skills: List<Int>,
    ): List<DecorationEntity>

    @Query(
        """
        SELECT * FROM decoration_skill
        WHERE decoration_id IN (:decorations)
        """
    )
    suspend fun getDecorationSkillList(
        decorations: List<Int>,
    ): List<DecorationSkillEntity>

}