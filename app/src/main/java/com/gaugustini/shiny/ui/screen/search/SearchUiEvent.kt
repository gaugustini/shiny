package com.gaugustini.shiny.ui.screen.search

import com.gaugustini.shiny.domain.model.Skill

sealed class SearchUiEvent {
    data class VillageRankExpanded(val expanded: Boolean) : SearchUiEvent()
    data class VillageRankChanged(val value: String) : SearchUiEvent()

    data class HunterRankExpanded(val expanded: Boolean) : SearchUiEvent()
    data class HunterRankChanged(val value: String) : SearchUiEvent()

    data class GenderExpanded(val expanded: Boolean) : SearchUiEvent()
    data class GenderChanged(val value: String) : SearchUiEvent()

    data class WeaponSlotExpanded(val expanded: Boolean) : SearchUiEvent()
    data class WeaponSlotChanged(val value: String) : SearchUiEvent()

    data class HunterTypeExpanded(val expanded: Boolean) : SearchUiEvent()
    data class HunterTypeChanged(val value: String) : SearchUiEvent()

    object OpenSkillSelection : SearchUiEvent()
    object CloseSkillSelection : SearchUiEvent()

    data class SkillSearchQueryChanged(val value: String) : SearchUiEvent()
    object ClearSkillSearchQuery : SearchUiEvent()

    data class SkillSelectionChanged(val skill: Skill) : SearchUiEvent()

    object SearchClicked : SearchUiEvent()
}