package com.gaugustini.shiny.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaugustini.shiny.domain.model.Query
import com.gaugustini.shiny.domain.repository.SkillRepository
import com.gaugustini.shiny.domain.solution.Solution
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val skillRepository: SkillRepository,
    private val solution: Solution
) : ViewModel() {

    var searchState by mutableStateOf(SearchState())

    init {
        loadSkills()
    }

    private fun loadSkills() {
        viewModelScope.launch {
            val skills = skillRepository.getSkillList(
                searchState.game,
                searchState.dataLanguage,
                searchState.hunterType
            )
            searchState = searchState.copy(skills = skills)
        }
    }

    fun onSearchStateChanged(state: SearchState) {
        var reloadSkills = false
        var clearQuerySkills = false

        if (searchState.game != state.game
            || searchState.dataLanguage != state.dataLanguage
            || searchState.hunterType != state.hunterType
        ) {
            reloadSkills = true
        }

        if (searchState.game != state.game || searchState.hunterType != state.hunterType) {
            clearQuerySkills = true
        }

        searchState = state

        if (reloadSkills) {
            loadSkills()
        }

        if (clearQuerySkills) {
            searchState = searchState.copy(querySkills = emptySet())
        }
    }

    fun startSearch() {
        searchState = searchState.copy(isSearching = true)

        val query = Query(
            game = searchState.game,
            hunterRank = searchState.hunterRank.toInt(),
            villageRank = searchState.villageRank.toInt(),
            weaponSlot = searchState.weaponSlot.toInt(),
            gender = searchState.gender,
            hunterType = searchState.hunterType,
            skills = searchState.querySkills.toList()
        )

        viewModelScope.launch(Dispatchers.Default) {
            if (solution.start(query)) {
                searchState = searchState.copy(isSearching = false, searchFinished = true)
            }
        }
    }

}