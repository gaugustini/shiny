package com.gaugustini.shiny.presentation.result

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.gaugustini.shiny.domain.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor() : ViewModel() {

    var resultState by mutableStateOf(ResultState())

    init {
        loadResults()
    }

    private fun loadResults() {
        val result = Result("Head", "Body", "Arms", "Waist", "Legs",
            mapOf("Decoration A" to 1, "Decoration B" to 2, "Decoration C" to 3))

        resultState = resultState.copy(results = listOf(result, result, result, result))
    }

}