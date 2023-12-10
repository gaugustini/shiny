package com.gaugustini.shiny.presentation.result

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaugustini.shiny.domain.repository.ResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val resultRepository: ResultRepository
) : ViewModel() {

    var resultState by mutableStateOf(ResultState())

    init {
        loadResults()
    }

    private fun loadResults() {
        viewModelScope.launch {
            resultState =
                resultState.copy(
                    results = resultRepository.getResults(resultState.dataLanguage),
                    isLoading = false
                )
        }
    }

}