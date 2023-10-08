package com.gaugustini.shiny.domain.model

data class Decoration(
    val id: Int,
    val slots: Int,
    val skills: Map<Int, Int>,
)