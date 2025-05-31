package com.gaugustini.shiny.domain.model

data class Armor(
    val id: Int,
    val category: Int,
    val numberOfSlots: Int,
    val skills: Map<Int, Int>,
)