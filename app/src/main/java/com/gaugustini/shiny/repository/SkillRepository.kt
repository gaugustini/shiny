package com.gaugustini.shiny.repository

import com.gaugustini.shiny.database.dao.SkillDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SkillRepository @Inject constructor(private val skillDao: SkillDao)