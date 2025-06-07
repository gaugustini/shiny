package com.gaugustini.shiny.ui.screen.search

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

    fun handleEvent(event: SearchUiEvent) {
        val state = _uiState.value
        when (event) {
            is SearchUiEvent.VillageRankExpanded ->
                _uiState.value = state.copy(villageRankExpanded = event.expanded)

            is SearchUiEvent.VillageRankChanged ->
                _uiState.value = state.copy(villageRank = event.value, villageRankExpanded = false)

            is SearchUiEvent.HunterRankExpanded ->
                _uiState.value = state.copy(hunterRankExpanded = event.expanded)

            is SearchUiEvent.HunterRankChanged ->
                _uiState.value = state.copy(hunterRank = event.value, hunterRankExpanded = false)

            is SearchUiEvent.GenderExpanded ->
                _uiState.value = state.copy(genderExpanded = event.expanded)

            is SearchUiEvent.GenderChanged ->
                _uiState.value = state.copy(gender = event.value, genderExpanded = false)

            is SearchUiEvent.WeaponSlotExpanded ->
                _uiState.value = state.copy(weaponSlotExpanded = event.expanded)

            is SearchUiEvent.WeaponSlotChanged ->
                _uiState.value = state.copy(weaponSlot = event.value, weaponSlotExpanded = false)

            is SearchUiEvent.HunterTypeExpanded ->
                _uiState.value = state.copy(hunterTypeExpanded = event.expanded)

            is SearchUiEvent.HunterTypeChanged -> {
                val newType = event.value
                val needsReload = newType != state.hunterType
                _uiState.value = state.copy(
                    hunterType = newType,
                    hunterTypeExpanded = false,
                    selectedSkillsExpanded = if (newType == "Blademaster") {
                        state.selectedSkillsBlade.isNotEmpty()
                    } else {
                        state.selectedSkillsGunner.isNotEmpty()
                    }
                )
                if (needsReload) loadSkills(if (newType == "Blademaster") 1 else 2)
            }

            is SearchUiEvent.OpenSkillSelection ->
                _uiState.value = state.copy(skillSelectionIsOpen = true)

            is SearchUiEvent.CloseSkillSelection ->
                _uiState.value = state.copy(
                    skillSelectionIsOpen = false,
                    skillsSearchQuery = "",
                    skillsFiltered = state.skills,
                    selectedSkillsExpanded = if (state.hunterType == "Blademaster") {
                        state.selectedSkillsBlade.isNotEmpty()
                    } else {
                        state.selectedSkillsGunner.isNotEmpty()
                    }
                )

            is SearchUiEvent.SkillSearchQueryChanged ->
                _uiState.value = state.copy(
                    skillsSearchQuery = event.value,
                    skillsFiltered = state.skills.filter {
                        it.name.lowercase().contains(event.value.lowercase())
                    }
                )

            is SearchUiEvent.ClearSkillSearchQuery ->
                _uiState.value = state.copy(
                    skillsSearchQuery = "",
                    skillsFiltered = state.skills
                )

            is SearchUiEvent.SkillSelectionChanged -> {
                val skill = event.skill
                if (state.hunterType == "Blademaster") {
                    val isSelected = state.selectedSkillsBlade.contains(skill)
                    val updatedList = if (isSelected) {
                        state.selectedSkillsBlade - skill
                    } else {
                        state.selectedSkillsBlade + skill
                    }
                    _uiState.value = state.copy(selectedSkillsBlade = updatedList)
                } else {
                    val isSelected = state.selectedSkillsGunner.contains(skill)
                    val updatedList = if (isSelected) {
                        state.selectedSkillsGunner - skill
                    } else {
                        state.selectedSkillsGunner + skill
                    }
                    _uiState.value = state.copy(selectedSkillsGunner = updatedList)
                }
            }

            is SearchUiEvent.SearchClicked ->
                startSearch()
        }
    }

}