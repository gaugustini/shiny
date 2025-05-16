package com.gaugustini.shiny.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gaugustini.shiny.database.dao.ArmorDao
import com.gaugustini.shiny.database.dao.DecorationDao
import com.gaugustini.shiny.database.dao.LanguageDao
import com.gaugustini.shiny.database.dao.ResultDao
import com.gaugustini.shiny.database.dao.SkillDao
import com.gaugustini.shiny.database.entity.ArmorEntity
import com.gaugustini.shiny.database.entity.DecorationEntity
import com.gaugustini.shiny.database.entity.LanguageEntity
import com.gaugustini.shiny.database.entity.ResultEntity
import com.gaugustini.shiny.database.entity.SkillEntity
import com.gaugustini.shiny.util.Converters

@Database(
    entities = [ArmorEntity::class, DecorationEntity::class, SkillEntity::class, LanguageEntity::class, ResultEntity::class],
    version = 1,
    exportSchema = true,
)
@TypeConverters(value = [Converters::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun armorDao(): ArmorDao

    abstract fun decorationDao(): DecorationDao

    abstract fun skillDao(): SkillDao

    abstract fun languageDao(): LanguageDao

    abstract fun resultDao(): ResultDao

}