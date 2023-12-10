package com.gaugustini.shiny.di

import com.gaugustini.shiny.data.repository.ArmorRepositoryImpl
import com.gaugustini.shiny.data.repository.DecorationRepositoryImpl
import com.gaugustini.shiny.data.repository.ResultRepositoryImpl
import com.gaugustini.shiny.data.repository.SkillRepositoryImpl
import com.gaugustini.shiny.domain.repository.ArmorRepository
import com.gaugustini.shiny.domain.repository.DecorationRepository
import com.gaugustini.shiny.domain.repository.ResultRepository
import com.gaugustini.shiny.domain.repository.SkillRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindArmorRepository(
        armorRepositoryImpl: ArmorRepositoryImpl
    ): ArmorRepository

    @Binds
    @Singleton
    abstract fun bindDecorationRepository(
        decorationRepositoryImpl: DecorationRepositoryImpl
    ): DecorationRepository

    @Binds
    @Singleton
    abstract fun bindSkillRepository(
        skillRepositoryImpl: SkillRepositoryImpl
    ): SkillRepository

    @Binds
    @Singleton
    abstract fun bindResultRepository(
        resultRepositoryImpl: ResultRepositoryImpl
    ): ResultRepository

}