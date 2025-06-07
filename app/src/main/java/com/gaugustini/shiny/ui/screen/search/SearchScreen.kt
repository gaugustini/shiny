package com.gaugustini.shiny.ui.screen.search

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaugustini.shiny.R
import com.gaugustini.shiny.ui.screen.search.components.CheckboxWithText
import com.gaugustini.shiny.ui.screen.search.components.ExpandableCard
import com.gaugustini.shiny.ui.screen.search.components.InputTextSearch
import com.gaugustini.shiny.ui.screen.search.components.SkillCard
import com.gaugustini.shiny.ui.theme.ShinyTheme

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    toResultScreen: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.searchFinished -> {
            toResultScreen()
        }

        uiState.isSearching -> {
            LoadingContent()
        }

        else -> {
            AnimatedContent(
                targetState = uiState.skillSelectionIsOpen,
                transitionSpec = {
                    if (targetState) {
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left) togetherWith
                                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                    } else {
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right) togetherWith
                                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                    }
                }
            ) { openSkillSelection ->
                if (openSkillSelection) {
                    SkillSelectionContent(
                        uiState,
                        onUiEvent = { viewModel.handleEvent(it) }
                    )
                } else {
                    SearchScreenContent(
                        uiState,
                        onUiEvent = { viewModel.handleEvent(it) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreenContent(
    uiState: SearchState,
    onUiEvent: (SearchUiEvent) -> Unit = {}
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    scrolledContainerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(start = 32.dp, end = 32.dp)
                .verticalScroll(rememberScrollState())
        ) {
            ExpandableCard(
                title = stringResource(R.string.village_stars),
                selectedValue = uiState.villageRank,
                expanded = uiState.villageRankExpanded,
                options = (1..9).map { it.toString() },
                onExpandChange = { onUiEvent(SearchUiEvent.VillageRankExpanded(it)) },
                onValueChange = { onUiEvent(SearchUiEvent.VillageRankChanged(it)) }
            )

            ExpandableCard(
                title = stringResource(R.string.hunter_rank),
                selectedValue = uiState.hunterRank,
                expanded = uiState.hunterRankExpanded,
                options = (1..9).map { it.toString() },
                onExpandChange = { onUiEvent(SearchUiEvent.HunterRankExpanded(it)) },
                onValueChange = { onUiEvent(SearchUiEvent.HunterRankChanged(it)) }
            )

            ExpandableCard(
                title = stringResource(R.string.gender),
                selectedValue = uiState.gender,
                expanded = uiState.genderExpanded,
                options = listOf(stringResource(R.string.male), stringResource(R.string.female)),
                onExpandChange = { onUiEvent(SearchUiEvent.GenderExpanded(it)) },
                onValueChange = { onUiEvent(SearchUiEvent.GenderChanged(it)) }
            )

            ExpandableCard(
                title = stringResource(R.string.weapon_slots),
                selectedValue = uiState.weaponSlot,
                expanded = uiState.weaponSlotExpanded,
                options = (0..3).map { it.toString() },
                onExpandChange = { onUiEvent(SearchUiEvent.WeaponSlotExpanded(it)) },
                onValueChange = { onUiEvent(SearchUiEvent.WeaponSlotChanged(it)) }
            )

            ExpandableCard(
                title = stringResource(R.string.hunter_type),
                selectedValue = uiState.hunterType,
                expanded = uiState.hunterTypeExpanded,
                options = listOf(
                    stringResource(R.string.blademaster),
                    stringResource(R.string.gunner)
                ),
                onExpandChange = { onUiEvent(SearchUiEvent.HunterTypeExpanded(it)) },
                onValueChange = { onUiEvent(SearchUiEvent.HunterTypeChanged(it)) }
            )

            SkillCard(
                title = stringResource(R.string.skills),
                expanded = uiState.selectedSkillsExpanded,
                items = if (uiState.hunterType == "Blademaster") {
                    uiState.selectedSkillsBlade.map { it.name }
                } else {
                    uiState.selectedSkillsGunner.map { it.name }
                },
                onClick = { onUiEvent(SearchUiEvent.OpenSkillSelection) }
            )

            Button(
                onClick = { onUiEvent(SearchUiEvent.SearchClicked) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 96.dp)
            ) {
                Text(
                    text = stringResource(R.string.search),
                    fontSize = 18.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkillSelectionContent(
    uiState: SearchState,
    onUiEvent: (SearchUiEvent) -> Unit = {}
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    BackHandler { onUiEvent(SearchUiEvent.CloseSkillSelection) }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.select_skills),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onUiEvent(SearchUiEvent.CloseSkillSelection) }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            tint = MaterialTheme.colorScheme.onBackground,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    scrolledContainerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        floatingActionButton = {
            Button(
                onClick = { onUiEvent(SearchUiEvent.CloseSkillSelection) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp, end = 40.dp)
            ) {
                Text(
                    text = stringResource(R.string.done),
                    fontSize = 18.sp
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            InputTextSearch(
                textInput = uiState.skillsSearchQuery,
                onValueChange = { onUiEvent(SearchUiEvent.SkillSearchQueryChanged(it)) },
                onClearInput = { onUiEvent(SearchUiEvent.ClearSkillSearchQuery) }
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(start = 48.dp, end = 48.dp, bottom = 96.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(uiState.skillsFiltered) { skill ->
                    CheckboxWithText(
                        text = skill.name,
                        checked = if (uiState.hunterType == "Blademaster") {
                            uiState.selectedSkillsBlade.contains(skill)
                        } else {
                            uiState.selectedSkillsGunner.contains(skill)
                        },
                        onCheckedChange = { onUiEvent(SearchUiEvent.SkillSelectionChanged(skill)) }
                    )
                }
            }
        }
    }
}

@Composable
fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SearchScreenContentPreview(
    @PreviewParameter(SearchScreenPreviewParamProvider::class) state: SearchState
) {
    ShinyTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            SearchScreenContent(state)
        }
    }
}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SkillsSelectionContentPreview(
    @PreviewParameter(SearchScreenPreviewParamProvider::class) state: SearchState
) {
    ShinyTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            SkillSelectionContent(state)
        }
    }
}

private class SearchScreenPreviewParamProvider : PreviewParameterProvider<SearchState> {
    override val values: Sequence<SearchState> = sequenceOf(
        SearchState()
    )
}
