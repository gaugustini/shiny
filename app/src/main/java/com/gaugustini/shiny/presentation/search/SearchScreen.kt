package com.gaugustini.shiny.presentation.search

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaugustini.shiny.presentation.theme.ShinyTheme

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    toResultScreen: () -> Unit = {}
) {
    val uiState by viewModel.searchState.collectAsState()

    if (uiState.searchFinished) {
        toResultScreen()
    } else {
        AnimatedContent(targetState = uiState.skillsSelectionIsOpen) { skillsSelectionIsOpen ->
            if (skillsSelectionIsOpen) {
                BackHandler {
                    viewModel.updateState(
                        uiState.copy(
                            skillsSelectionIsOpen = false,
                            skillsSearchQuery = "",
                            skillsFiltered = uiState.skills,
                            selectedSkillsExpanded = uiState.selectedSkills.isNotEmpty()
                        )
                    )
                }
                SkillsSelectionContent(
                    uiState,
                    updateState = { viewModel.updateState(it) },
                )
            } else {
                SearchScreenContent(
                    uiState,
                    updateState = { viewModel.updateState(it) },
                    onSearchClick = {
                        // Start Search
                    }
                )
            }
        }
    }
}

@Composable
fun SearchScreenContent(
    uiState: SearchState,
    updateState: (SearchState) -> Unit = {},
    onSearchClick: () -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "Shiny", fontSize = 24.sp)

        ExpandableCard(
            title = "Village Stars",
            selectedValue = uiState.villageRank,
            expanded = uiState.villageRankExpanded,
            options = (1..9).map { it.toString() },
            onExpandChange = { updateState(uiState.copy(villageRankExpanded = it)) },
            onValueChange = {
                updateState(uiState.copy(villageRank = it, villageRankExpanded = false))
            }
        )

        ExpandableCard(
            title = "Hunter Rank",
            selectedValue = uiState.hunterRank,
            expanded = uiState.hunterRankExpanded,
            options = (1..9).map { it.toString() },
            onExpandChange = { updateState(uiState.copy(hunterRankExpanded = it)) },
            onValueChange = {
                updateState(uiState.copy(hunterRank = it, hunterRankExpanded = false))
            }
        )

        ExpandableCard(
            title = "Gender",
            selectedValue = uiState.gender,
            expanded = uiState.genderExpanded,
            options = listOf("Male", "Female"),
            onExpandChange = { updateState(uiState.copy(genderExpanded = it)) },
            onValueChange = {
                updateState(uiState.copy(gender = it, genderExpanded = false))
            }
        )

        ExpandableCard(
            title = "Weapon Slots",
            selectedValue = uiState.weaponSlot,
            expanded = uiState.weaponSlotExpanded,
            options = (0..3).map { it.toString() },
            onExpandChange = { updateState(uiState.copy(weaponSlotExpanded = it)) },
            onValueChange = {
                updateState(uiState.copy(weaponSlot = it, weaponSlotExpanded = false))
            }
        )

        ExpandableCard(
            title = "Hunter Type",
            selectedValue = uiState.hunterType,
            expanded = uiState.hunterTypeExpanded,
            options = listOf("Blademaster", "Gunner"),
            onExpandChange = { updateState(uiState.copy(hunterTypeExpanded = it)) },
            onValueChange = {
                updateState(uiState.copy(hunterType = it, hunterTypeExpanded = false))
            }
        )

        ExpandableCard(
            title = "Skills",
            selectedValue = "",
            expanded = uiState.selectedSkillsExpanded,
            options = uiState.selectedSkills,
            onExpandChange = { updateState(uiState.copy(skillsSelectionIsOpen = true)) },
            onValueChange = { }
        )

        Button(
            onClick = { onSearchClick() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Search", fontSize = 18.sp)
        }
    }
}

@Composable
fun SkillsSelectionContent(
    uiState: SearchState,
    updateState: (SearchState) -> Unit = {},
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Select Skills...", fontSize = 24.sp)

        TextField(
            value = uiState.skillsSearchQuery,
            onValueChange = { value ->
                updateState(
                    uiState.copy(
                        skillsSearchQuery = value,
                        skillsFiltered = uiState.skills.filter {
                            it.lowercase().contains(value.lowercase())
                        }
                    )
                )
            },
            placeholder = { Text("Search...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        )

        LazyColumn(modifier = Modifier.weight(1f)) {
            itemsIndexed(uiState.skillsFiltered) { _, skill ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Checkbox(
                        checked = uiState.selectedSkills.contains(skill),
                        onCheckedChange = { checked ->
                            if (checked) {
                                updateState(uiState.copy(selectedSkills = uiState.selectedSkills + skill))
                            } else {
                                updateState(uiState.copy(selectedSkills = uiState.selectedSkills - skill))
                            }
                        }
                    )
                    Text(text = skill, textAlign = TextAlign.Center)
                }
            }
        }

        Button(
            onClick = {
                updateState(
                    uiState.copy(
                        skillsSelectionIsOpen = false,
                        skillsSearchQuery = "",
                        skillsFiltered = uiState.skills,
                        selectedSkillsExpanded = uiState.selectedSkills.isNotEmpty()
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(text = "Done", fontSize = 18.sp)
        }
    }
}

@Composable
fun ExpandableCard(
    title: String,
    selectedValue: String,
    expanded: Boolean,
    options: List<String>,
    onExpandChange: (Boolean) -> Unit,
    onValueChange: (String) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { onExpandChange(!expanded) }
            ) {
                Text(text = title, fontWeight = FontWeight.Bold)
                Row {
                    Text(text = selectedValue, modifier = Modifier.padding(end = 8.dp))
                    Icon(
                        imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
            }

            if (expanded) {
                Column(modifier = Modifier.background(Color(0xFFF2E6FF))) {
                    options.forEach { option ->
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .background(
                                    if (option == selectedValue) Color(0xFFD1B3FF) else Color.Transparent
                                )
                                .fillMaxWidth()
                                .padding(12.dp)
                                .clickable { onValueChange(option) }
                        ) {
                            Text(text = option, textAlign = TextAlign.Center)
                        }
                    }
                }
            }
        }
    }
}

@Preview(name = "Light Mode")
//@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SearchScreenPreview(
    @PreviewParameter(SearchScreenPreviewParamProvider::class) state: SearchState
) {
    ShinyTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            SearchScreenContent(state)
            //SkillsSelectionContent(state)
        }
    }
}

private class SearchScreenPreviewParamProvider : PreviewParameterProvider<SearchState> {
    override val values: Sequence<SearchState> = sequenceOf(
        SearchState()
    )
}
