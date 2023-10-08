package com.gaugustini.shiny.domain.model

data class Armor(
    val id: Int,
    val slots: Int,
    val skills: Map<Int, Int>,
)