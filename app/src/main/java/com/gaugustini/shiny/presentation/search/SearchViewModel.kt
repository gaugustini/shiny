package com.gaugustini.shiny.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {

    private val _searchState = MutableStateFlow(SearchState())
    val searchState: StateFlow<SearchState> = _searchState.asStateFlow()

    fun updateState(newState: SearchState) {
        _searchState.value = newState
    }

    private fun loadSkills() {
        // Load skills from database
    }

    fun startSearch() {
        _searchState.value = _searchState.value.copy(isSearching = true)
        viewModelScope.launch(Dispatchers.Default) {
            delay(3000) // Simulate search
            _searchState.value = _searchState.value.copy(isSearching = false, searchFinished = true)
            delay(1000) // Need to find a better way to change state after going to Result Screen
            _searchState.value = _searchState.value.copy(searchFinished = false)
        }
    }

}
