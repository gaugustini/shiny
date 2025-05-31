package com.gaugustini.shiny.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.gaugustini.shiny.data.database.entity.ArmorEntity
import com.gaugustini.shiny.data.database.entity.ArmorSkillEntity

@Dao
interface ArmorDao {

    @Query(
        """
        SELECT DISTINCT armor.* FROM armor
        JOIN armor_skill ON armor_skill.armor_id = armor.id
        WHERE
        armor.game = :game AND
        armor.armor_type = :armorType AND
        (armor.hunter_rank <= :hunterRank OR armor.village_stars <= :villageStars) AND
        armor.gender IN (0, :gender) AND
        armor.hunter_type IN (0, :hunterType) AND
        armor_skill.skill_tree_id IN (:skills) AND
        armor_skill.point_value > 0
        """
    )
    suspend fun getArmorList(
        game: Int,
        armorType: Int,
        hunterRank: Int,
        villageStars: Int,
        gender: Int,
        hunterType: Int,
        skills: List<Int>,
    ): List<ArmorEntity>

    @Query(
        """
        SELECT * FROM armor_skill
        WHERE armor_id IN (:armors)
        """
    )
    suspend fun getArmorSkillList(
        armors: List<Int>,
    ): List<ArmorSkillEntity>

}