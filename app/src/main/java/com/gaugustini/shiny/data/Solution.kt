package com.gaugustini.shiny.data

data class Solution(
    val head: String,
    val body: String,
    val arms: String,
    val waist: String,
    val legs: String,
    val decorations: Map<String, Int>,
)
