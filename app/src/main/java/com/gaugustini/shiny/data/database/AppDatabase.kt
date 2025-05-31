package com.gaugustini.shiny.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gaugustini.shiny.data.database.dao.ArmorDao
import com.gaugustini.shiny.data.database.dao.DecorationDao
import com.gaugustini.shiny.data.database.dao.ResultDao
import com.gaugustini.shiny.data.database.dao.SkillDao
import com.gaugustini.shiny.data.database.entity.ArmorEntity
import com.gaugustini.shiny.data.database.entity.ArmorRecipeEntity
import com.gaugustini.shiny.data.database.entity.ArmorSkillEntity
import com.gaugustini.shiny.data.database.entity.DecorationEntity
import com.gaugustini.shiny.data.database.entity.DecorationRecipeEntity
import com.gaugustini.shiny.data.database.entity.DecorationSkillEntity
import com.gaugustini.shiny.data.database.entity.MaterialEntity
import com.gaugustini.shiny.data.database.entity.ResultDecorationEntity
import com.gaugustini.shiny.data.database.entity.ResultEntity
import com.gaugustini.shiny.data.database.entity.SkillEntity
import com.gaugustini.shiny.data.database.entity.SkillTreeEntity

@Database(
    entities = [
        ArmorEntity::class, ArmorRecipeEntity::class, ArmorSkillEntity::class,
        DecorationEntity::class, DecorationRecipeEntity::class, DecorationSkillEntity::class,
        MaterialEntity::class, SkillTreeEntity::class, SkillEntity::class,
        ResultEntity::class, ResultDecorationEntity::class
    ],
    version = 1,
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun armorDao(): ArmorDao

    abstract fun decorationDao(): DecorationDao

    abstract fun skillDao(): SkillDao

    abstract fun resultDao(): ResultDao

}