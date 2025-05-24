package com.gaugustini.shiny.data

data class Armor(
    val nameID: Int,
    val category: Int,
    val slots: Int,
    val skills: Map<Int, Int>,
)