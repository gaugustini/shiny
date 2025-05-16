package com.gaugustini.shiny.di

import android.content.Context
import androidx.room.Room
import com.gaugustini.shiny.database.AppDatabase
import com.gaugustini.shiny.database.dao.ArmorDao
import com.gaugustini.shiny.database.dao.DecorationDao
import com.gaugustini.shiny.database.dao.LanguageDao
import com.gaugustini.shiny.database.dao.ResultDao
import com.gaugustini.shiny.database.dao.SkillDao
import com.gaugustini.shiny.util.Converters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        typeConverters: Converters
    ): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext, AppDatabase::class.java, "data.db"
        )
            .createFromAsset("data.db")
            .fallbackToDestructiveMigration()
            .addTypeConverter(typeConverters)
            .build()
    }

    @Provides
    @Singleton
    fun provideConverters(): Converters {
        return Converters()
    }

    @Provides
    @Singleton
    fun provideArmorPieceDao(appDatabase: AppDatabase): ArmorDao {
        return appDatabase.armorDao()
    }

    @Provides
    @Singleton
    fun provideDecorationDao(appDatabase: AppDatabase): DecorationDao {
        return appDatabase.decorationDao()
    }

    @Provides
    @Singleton
    fun provideSkillDao(appDatabase: AppDatabase): SkillDao {
        return appDatabase.skillDao()
    }

    @Provides
    @Singleton
    fun provideLanguageDao(appDatabase: AppDatabase): LanguageDao {
        return appDatabase.languageDao()
    }

    @Provides
    @Singleton
    fun provideResultDao(appDatabase: AppDatabase): ResultDao {
        return appDatabase.resultDao()
    }

}