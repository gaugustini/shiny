package com.gaugustini.shiny.data

import com.gaugustini.shiny.util.ArmorCategory

data class Armor(
    val nameID: Int,
    val category: ArmorCategory,
    val slots: Int,
    val skills: Map<Int, Int>,
)