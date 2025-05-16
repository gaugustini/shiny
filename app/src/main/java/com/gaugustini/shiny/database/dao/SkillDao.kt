package com.gaugustini.shiny.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.gaugustini.shiny.database.entity.SkillWithLanguage

@Dao
interface SkillDao {

    @Transaction
    @Query(
        """
        SELECT
            skill.skill_id,
            language.name AS name,
            skill.family,
            skill.points
        FROM skill
        JOIN language ON skill.name = language.name_id AND language.language_code = :language
        WHERE game = :game AND points >= 10 AND type IN (0, :hunterType)
        ORDER BY family
        """
    )
    suspend fun getSkillWithLanguageList(
        game: Int,
        language: String,
        hunterType: Int
    ): List<SkillWithLanguage>

}