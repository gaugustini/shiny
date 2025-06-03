package com.gaugustini.shiny.presentation.search

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaugustini.shiny.R
import com.gaugustini.shiny.presentation.theme.ShinyTheme

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    toResultScreen: () -> Unit = {}
) {
    val uiState by viewModel.searchState.collectAsState()

    when {
        uiState.searchFinished -> {
            toResultScreen()
        }

        uiState.isSearching -> {
            LoadingContent()
        }

        else -> {
            AnimatedContent(
                targetState = uiState.skillsSelectionIsOpen,
                transitionSpec = {
                    if (targetState) {
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left) togetherWith
                                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                    } else {
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right) togetherWith
                                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                    }
                }
            ) { skillsSelectionIsOpen ->
                if (skillsSelectionIsOpen) {
                    SkillsSelectionContent(
                        uiState,
                        updateState = { viewModel.updateState(it) },
                    )
                } else {
                    SearchScreenContent(
                        uiState,
                        updateState = { viewModel.updateState(it) },
                        onSearchClick = { viewModel.startSearch() }
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
    updateState: (SearchState) -> Unit = {},
    onSearchClick: () -> Unit = {}
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
                scrollBehavior = scrollBehavior
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
                onExpandChange = { updateState(uiState.copy(villageRankExpanded = it)) },
                onValueChange = {
                    updateState(uiState.copy(villageRank = it, villageRankExpanded = false))
                }
            )

            ExpandableCard(
                title = stringResource(R.string.hunter_rank),
                selectedValue = uiState.hunterRank,
                expanded = uiState.hunterRankExpanded,
                options = (1..9).map { it.toString() },
                onExpandChange = { updateState(uiState.copy(hunterRankExpanded = it)) },
                onValueChange = {
                    updateState(uiState.copy(hunterRank = it, hunterRankExpanded = false))
                }
            )

            ExpandableCard(
                title = stringResource(R.string.gender),
                selectedValue = uiState.gender,
                expanded = uiState.genderExpanded,
                options = listOf(stringResource(R.string.male), stringResource(R.string.female)),
                onExpandChange = { updateState(uiState.copy(genderExpanded = it)) },
                onValueChange = {
                    updateState(uiState.copy(gender = it, genderExpanded = false))
                }
            )

            ExpandableCard(
                title = stringResource(R.string.weapon_slots),
                selectedValue = uiState.weaponSlot,
                expanded = uiState.weaponSlotExpanded,
                options = (0..3).map { it.toString() },
                onExpandChange = { updateState(uiState.copy(weaponSlotExpanded = it)) },
                onValueChange = {
                    updateState(uiState.copy(weaponSlot = it, weaponSlotExpanded = false))
                }
            )

            ExpandableCard(
                title = stringResource(R.string.hunter_type),
                selectedValue = uiState.hunterType,
                expanded = uiState.hunterTypeExpanded,
                options = listOf(
                    stringResource(R.string.blademaster),
                    stringResource(R.string.gunner)
                ),
                onExpandChange = { updateState(uiState.copy(hunterTypeExpanded = it)) },
                onValueChange = {
                    updateState(uiState.copy(hunterType = it, hunterTypeExpanded = false))
                }
            )

            SkillCard(
                title = stringResource(R.string.skills),
                expanded = uiState.selectedSkillsExpanded,
                items = if (uiState.hunterType == "Blademaster") {
                    uiState.selectedSkillsBlade.map { it.name }
                } else {
                    uiState.selectedSkillsGunner.map { it.name }
                },
                onClick = { updateState(uiState.copy(skillsSelectionIsOpen = true)) }
            )

            Button(
                onClick = { onSearchClick() },
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
fun SkillsSelectionContent(
    uiState: SearchState,
    updateState: (SearchState) -> Unit = {}
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val focusManager = LocalFocusManager.current
    val clearStateSkillsSelection = uiState.copy(
        skillsSelectionIsOpen = false,
        skillsSearchQuery = "",
        skillsFiltered = uiState.skills,
        selectedSkillsExpanded = if (uiState.hunterType == "Blademaster") {
            uiState.selectedSkillsBlade.isNotEmpty()
        } else {
            uiState.selectedSkillsGunner.isNotEmpty()
        }
    )

    BackHandler { updateState(clearStateSkillsSelection) }

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
                        onClick = { updateState(clearStateSkillsSelection) }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            tint = MaterialTheme.colorScheme.onBackground,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            Button(
                onClick = { updateState(clearStateSkillsSelection) },
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
            OutlinedTextField(
                placeholder = { Text(stringResource(R.string.search)) },
                value = uiState.skillsSearchQuery,
                onValueChange = { value ->
                    updateState(
                        uiState.copy(
                            skillsSearchQuery = value,
                            skillsFiltered = uiState.skills.filter {
                                it.name.lowercase().contains(value.lowercase())
                            }
                        )
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = { focusManager.clearFocus() }
                ),
                shape = RoundedCornerShape(16.dp),
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = {
                    if (uiState.skillsSearchQuery.isNotEmpty()) {
                        IconButton(onClick = {
                            updateState(
                                uiState.copy(
                                    skillsSearchQuery = "",
                                    skillsFiltered = uiState.skills
                                )
                            )
                            focusManager.clearFocus()
                        }) {
                            Icon(
                                Icons.Filled.Clear,
                                contentDescription = stringResource(R.string.clear_text)
                            )
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, top = 16.dp, bottom = 16.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(start = 48.dp, end = 48.dp, bottom = 96.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(uiState.skillsFiltered) { skill ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable {
                                val isSelected = if (uiState.hunterType == "Blademaster") {
                                    uiState.selectedSkillsBlade.contains(skill)
                                } else {
                                    uiState.selectedSkillsGunner.contains(skill)
                                }
                                val updatedList = if (isSelected) {
                                    if (uiState.hunterType == "Blademaster") {
                                        uiState.selectedSkillsBlade - skill
                                    } else {
                                        uiState.selectedSkillsGunner - skill
                                    }
                                } else {
                                    if (uiState.hunterType == "Blademaster") {
                                        uiState.selectedSkillsBlade + skill
                                    } else {
                                        uiState.selectedSkillsGunner + skill
                                    }
                                }
                                if (uiState.hunterType == "Blademaster") {
                                    updateState(uiState.copy(selectedSkillsBlade = updatedList))
                                } else {
                                    updateState(uiState.copy(selectedSkillsGunner = updatedList))
                                }
                            }
                            .fillMaxWidth()
                    ) {
                        Checkbox(
                            checked = if (uiState.hunterType == "Blademaster") {
                                uiState.selectedSkillsBlade.contains(skill)
                            } else {
                                uiState.selectedSkillsGunner.contains(skill)
                            },
                            onCheckedChange = null,
                            modifier = Modifier.padding(16.dp)
                        )
                        Text(
                            text = skill.name,
                            fontSize = 18.sp
                        )
                    }
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

@Composable
fun ExpandableCard(
    title: String,
    selectedValue: String,
    expanded: Boolean,
    options: List<String>,
    onExpandChange: (Boolean) -> Unit,
    onValueChange: (String) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .clickable { onExpandChange(!expanded) }
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                Row {
                    Text(
                        text = selectedValue,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(end = 12.dp)
                    )
                    Icon(
                        imageVector = if (expanded) {
                            Icons.Default.KeyboardArrowUp
                        } else {
                            Icons.Default.KeyboardArrowDown
                        },
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        contentDescription = null
                    )
                }
            }

            if (expanded) {
                Column {
                    options.forEach { option ->
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .clickable { onValueChange(option) }
                                .background(
                                    if (option == selectedValue) {
                                        MaterialTheme.colorScheme.primary
                                    } else {
                                        MaterialTheme.colorScheme.surfaceContainer
                                    }
                                )
                                .fillMaxWidth()
                                .padding(12.dp)
                        ) {
                            Text(
                                text = option,
                                fontSize = 18.sp,
                                color = if (option == selectedValue) {
                                    MaterialTheme.colorScheme.onPrimary
                                } else {
                                    MaterialTheme.colorScheme.onSurface
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SkillCard(
    title: String,
    expanded: Boolean,
    items: List<String>,
    onClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .clickable { onClick() }
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    contentDescription = null
                )

            }

            if (expanded) {
                Column {
                    items.forEach { item ->
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.surfaceContainer)
                                .fillMaxWidth()
                                .padding(12.dp)
                        ) {
                            Text(
                                text = item,
                                fontSize = 18.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }
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
            SkillsSelectionContent(state)
        }
    }
}

private class SearchScreenPreviewParamProvider : PreviewParameterProvider<SearchState> {
    override val values: Sequence<SearchState> = sequenceOf(
        SearchState()
    )
}
