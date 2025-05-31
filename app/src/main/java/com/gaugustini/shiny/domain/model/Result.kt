package com.gaugustini.shiny.domain.model

data class Result(
    val head: String,
    val body: String,
    val arms: String,
    val waist: String,
    val legs: String,
    val decorations: Map<String, Int>,
)