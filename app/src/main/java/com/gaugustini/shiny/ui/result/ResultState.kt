package com.gaugustini.shiny.ui.result

import com.gaugustini.shiny.data.Solution

data class ResultState(
    val results: List<Solution> = emptyList(),
)
