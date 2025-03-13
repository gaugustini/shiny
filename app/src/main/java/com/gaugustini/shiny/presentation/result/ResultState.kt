package com.gaugustini.shiny.presentation.result

import com.gaugustini.shiny.domain.model.Solution

data class ResultState(
    val results: List<Solution> = emptyList(),
)
