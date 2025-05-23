package com.gaugustini.shiny.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gaugustini.shiny.database.dao.ArmorDao
import com.gaugustini.shiny.database.dao.DecorationDao
import com.gaugustini.shiny.database.dao.ResultDao
import com.gaugustini.shiny.database.dao.SkillDao
import com.gaugustini.shiny.database.entity.ArmorEntity
import com.gaugustini.shiny.database.entity.ArmorMaterialEntity
import com.gaugustini.shiny.database.entity.ArmorSkillEntity
import com.gaugustini.shiny.database.entity.DecorationEntity
import com.gaugustini.shiny.database.entity.DecorationMaterialEntity
import com.gaugustini.shiny.database.entity.DecorationSkillEntity
import com.gaugustini.shiny.database.entity.MaterialEntity
import com.gaugustini.shiny.database.entity.ResultEntity
import com.gaugustini.shiny.database.entity.ResultJewelEntity
import com.gaugustini.shiny.database.entity.SkillEntity
import com.gaugustini.shiny.database.entity.SkillTreeEntity

@Database(
    entities = [
        ArmorEntity::class, ArmorMaterialEntity::class, ArmorSkillEntity::class,
        DecorationEntity::class, DecorationMaterialEntity::class, DecorationSkillEntity::class,
        MaterialEntity::class, SkillTreeEntity::class, SkillEntity::class,
        ResultEntity::class, ResultJewelEntity::class
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