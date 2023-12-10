package com.gaugustini.shiny.presentation.result

import com.gaugustini.shiny.domain.model.Result
import com.gaugustini.shiny.util.DataLanguage

data class ResultState(
    val dataLanguage: DataLanguage = DataLanguage.ENGLISH,
    val results: List<Result> = emptyList(),
    val isLoading: Boolean = true,
)