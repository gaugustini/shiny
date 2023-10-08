package com.gaugustini.shiny.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gaugustini.shiny.data.dao.ArmorDao
import com.gaugustini.shiny.data.dao.DecorationDao
import com.gaugustini.shiny.data.dao.LanguageDao
import com.gaugustini.shiny.data.dao.SkillDao
import com.gaugustini.shiny.data.entity.ArmorEntity
import com.gaugustini.shiny.data.entity.DecorationEntity
import com.gaugustini.shiny.data.entity.LanguageEntity
import com.gaugustini.shiny.data.entity.SkillEntity
import com.gaugustini.shiny.data.mapper.Converters

@Database(
    entities = [ArmorEntity::class, DecorationEntity::class, SkillEntity::class, LanguageEntity::class],
    version = 1,
    exportSchema = true,
)
@TypeConverters(value = [Converters::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun armorDao(): ArmorDao

    abstract fun decorationDao(): DecorationDao

    abstract fun skillDao(): SkillDao

    abstract fun languageDao(): LanguageDao

}