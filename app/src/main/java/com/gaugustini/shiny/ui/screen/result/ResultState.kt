package com.gaugustini.shiny.ui.screen.result

import com.gaugustini.shiny.domain.model.Result

data class ResultState(
    val results: List<Result> = emptyList(),
)