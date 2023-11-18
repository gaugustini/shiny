package com.gaugustini.shiny.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {

    var searchState by mutableStateOf(SearchState())

    init {
        loadSkills()
    }

    fun onSearchStateChanged(state: SearchState) {
        searchState = state
    }

    private fun loadSkills() {
        searchState = searchState.copy(skills = listOf("SKill1", "SKill2", "SKill3", "SKill4", "SKill5"))
    }

}