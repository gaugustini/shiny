package com.gaugustini.shiny.presentation.result

import com.gaugustini.shiny.domain.model.Result

data class ResultState(
    val results: List<Result> = emptyList(),
)