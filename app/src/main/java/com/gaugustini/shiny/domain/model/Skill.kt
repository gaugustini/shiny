package com.gaugustini.shiny.domain.model

data class Skill(
    val id: Int,
    val name: String,
    val skillTree: Int,
    val requiredPoints: Int,
)