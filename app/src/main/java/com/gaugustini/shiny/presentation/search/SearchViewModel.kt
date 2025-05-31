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

    private val _searchState = MutableStateFlow(SearchState())
    val searchState: StateFlow<SearchState> = _searchState.asStateFlow()

    init {
        loadSkills(1)
    }

    private fun loadSkills(hunterType: Int) {
        viewModelScope.launch {
            val skills = withContext(Dispatchers.IO) {
                skillRepository.getSkillList(hunterType)
            }
            _searchState.update { it.copy(skills = skills) }
        }
    }

    fun updateState(newState: SearchState) {
        if (newState.hunterType != _searchState.value.hunterType) {
            loadSkills(if (newState.hunterType == "Blademaster") 1 else 2)
        }

        _searchState.value = newState
    }

    fun startSearch() {
        val searchOptions = SearchOptions(
            game = 0,
            hunterRank = _searchState.value.hunterRank.toInt(),
            villageRank = _searchState.value.villageRank.toInt(),
            weaponSlot = _searchState.value.weaponSlot.toInt(),
            gender = if (_searchState.value.gender == "Male") 1 else 2,
            hunterType = if (_searchState.value.hunterType == "Blademaster") 1 else 2,
            skills = _searchState.value.selectedSkills
        )
        viewModelScope.launch {
            _searchState.value = _searchState.value.copy(isSearching = true)

            val searchFinished = withContext(Dispatchers.IO) {
                solution.start(searchOptions)
            }
            _searchState.value =
                _searchState.value.copy(isSearching = false, searchFinished = searchFinished)
            delay(1000) // Need to find a better way to change state after going to Result Screen
            _searchState.value = _searchState.value.copy(searchFinished = false)
        }
    }

}