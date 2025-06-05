package com.gaugustini.shiny.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaugustini.shiny.data.repository.SkillRepository
import com.gaugustini.shiny.domain.model.SearchOptions
import com.gaugustini.shiny.domain.solution.Solution
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val skillRepository: SkillRepository,
    private val solution: Solution
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchState())
    val uiState: StateFlow<SearchState> = _uiState.asStateFlow()

    init {
        loadSkills(1)
    }

    private fun loadSkills(hunterType: Int) {
        viewModelScope.launch {
            val skills = skillRepository.getSkillList(hunterType)
            _uiState.update { it.copy(skills = skills, skillsFiltered = skills) }
        }
    }

    fun updateState(newState: SearchState) {
        if (newState.hunterType != _uiState.value.hunterType) {
            loadSkills(if (newState.hunterType == "Blademaster") 1 else 2)
        }

        _uiState.value = newState
    }

    fun startSearch() {
        val state = _uiState.value
        val searchOptions = SearchOptions(
            game = 0,
            hunterRank = state.hunterRank.toInt(),
            villageRank = state.villageRank.toInt(),
            weaponSlot = state.weaponSlot.toInt(),
            gender = if (state.gender == "Male") 1 else 2,
            hunterType = if (state.hunterType == "Blademaster") 1 else 2,
            skills = if (state.hunterType == "Blademaster") {
                state.selectedSkillsBlade
            } else {
                state.selectedSkillsGunner
            }
        )
        viewModelScope.launch {
            _uiState.update { it.copy(isSearching = true) }
            withContext(Dispatchers.Default) {
                solution.start(searchOptions)
            }
            _uiState.update { it.copy(isSearching = false, searchFinished = true) }
            delay(1000) // Need to find a better way to change state after going to Result Screen
            _uiState.update { it.copy(searchFinished = false) }
        }
    }

}