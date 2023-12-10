package com.gaugustini.shiny.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaugustini.shiny.R
import com.gaugustini.shiny.presentation.theme.ShinyTheme
import com.gaugustini.shiny.util.DataLanguage
import com.gaugustini.shiny.util.Game
import com.gaugustini.shiny.util.Gender
import com.gaugustini.shiny.util.HunterType

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    toResultScreen: () -> Unit = {}
) {
    val state = viewModel.searchState

    if (state.searchFinished) {
        viewModel.onSearchStateChanged(state.copy(searchFinished = false))
        LaunchedEffect(Unit) { toResultScreen() }
    }

    SearchScreenContent(
        state,
        onStateChanged = { viewModel.onSearchStateChanged(it) },
        onSearchClick = { viewModel.startSearch() }
    )
}

@Composable
fun SearchScreenContent(
    state: SearchState,
    onStateChanged: (SearchState) -> Unit = {},
    onSearchClick: () -> Unit = {}
) {
    Column(
        Modifier.verticalScroll(rememberScrollState()),
    ) {
        SearchingIndicator(state)
        GameChoice(state, onStateChanged)
        RankChoice(state, onStateChanged)
        SlotChoice(state, onStateChanged)
        HunterTypeChoice(state, onStateChanged)
        GenderChoice(state, onStateChanged)
        Button(onClick = onSearchClick) {
            Text(text = "Search")
        }
        SkillChoice(state, onStateChanged)
    }
}

@Composable
fun SearchingIndicator(state: SearchState) {
    if (state.isSearching) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

@Composable
fun GameChoice(state: SearchState, onStateChanged: (SearchState) -> Unit) {
    val games = stringArrayResource(id = R.array.game_titles)
    val languages = when (state.game) {
        Game.MHF1 -> stringArrayResource(id = R.array.data_languages_mhf1)
        Game.MHFU -> stringArrayResource(id = R.array.data_languages_mhfu)
        Game.MHP3 -> stringArrayResource(id = R.array.data_languages_mhp3)
        Game.MH3U -> stringArrayResource(id = R.array.data_languages_mh3u)
        Game.MH4 -> stringArrayResource(id = R.array.data_languages_mh4)
        Game.MH4U -> stringArrayResource(id = R.array.data_languages_mh4u)
        Game.MHGEN -> stringArrayResource(id = R.array.data_languages_mhgen)
        Game.MHGU -> stringArrayResource(id = R.array.data_languages_mhgu)
    }

    Text(text = "Game")
    games.forEachIndexed { index, game ->
        Row {
            RadioButton(
                selected = state.game.value == index,
                onClick = {
                    onStateChanged(
                        state.copy(
                            game = Game.fromIntToGame(index),
                            dataLanguage = DataLanguage.ENGLISH
                        )
                    )
                }
            )
            Text(text = game)
        }
    }

    Text(text = "Language")
    languages.forEach { language ->
        Row {
            RadioButton(
                selected = state.dataLanguage == DataLanguage.fromStringToDataLanguage(language),
                onClick = {
                    onStateChanged(
                        state.copy(dataLanguage = DataLanguage.fromStringToDataLanguage(language))
                    )
                }
            )
            Text(text = language)
        }
    }
}

@Composable
fun RankChoice(state: SearchState, onStateChanged: (SearchState) -> Unit) {
    Text(text = "Ranks")
    TextField(
        value = state.villageRank,
        onValueChange = {
            onStateChanged(state.copy(villageRank = it))
        },
        label = { Text(text = "Village Rank") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
    )
    TextField(
        value = state.hunterRank,
        onValueChange = {
            onStateChanged(state.copy(hunterRank = it))
        },
        label = { Text(text = "Hunter Rank") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
    )

}

@Composable
fun SlotChoice(state: SearchState, onStateChanged: (SearchState) -> Unit) {
    Text(text = "Slots")
    TextField(
        value = state.weaponSlot,
        onValueChange = {
            onStateChanged(state.copy(weaponSlot = it))
        },
        label = { Text(text = "Slots") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
    )
}

@Composable
fun HunterTypeChoice(state: SearchState, onStateChanged: (SearchState) -> Unit) {
    Text(text = "Hunter Type")
    Row {
        RadioButton(
            selected = state.hunterType == HunterType.BLADEMASTER,
            onClick = {
                onStateChanged(state.copy(hunterType = HunterType.BLADEMASTER))
            }
        )
        Text(text = "Blademaster")
    }
    Row {
        RadioButton(
            selected = state.hunterType == HunterType.GUNNER,
            onClick = {
                onStateChanged(state.copy(hunterType = HunterType.GUNNER))
            }
        )
        Text(text = "Gunner")
    }
}

@Composable
fun GenderChoice(state: SearchState, onStateChanged: (SearchState) -> Unit) {
    Text(text = "Gender")
    Row {
        RadioButton(
            selected = state.gender == Gender.MALE,
            onClick = {
                onStateChanged(state.copy(gender = Gender.MALE))
            }
        )
        Text(text = "Male")
    }
    Row {
        RadioButton(
            selected = state.gender == Gender.FEMALE,
            onClick = {
                onStateChanged(state.copy(gender = Gender.FEMALE))
            }
        )
        Text(text = "Female")
    }
}

@Composable
fun SkillChoice(state: SearchState, onStateChanged: (SearchState) -> Unit) {
    LazyColumn(modifier = Modifier.height(1_000.dp)) {
        items(state.skills) { skill ->
            Row {
                Checkbox(
                    checked = state.querySkills.contains(skill),
                    onCheckedChange = { checked ->
                        if (checked) {
                            onStateChanged(state.copy(querySkills = state.querySkills + skill))
                        } else {
                            onStateChanged(state.copy(querySkills = state.querySkills - skill))
                        }
                    })
                Text(text = skill.name)
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
        }
    }
}

private class SearchScreenPreviewParamProvider : PreviewParameterProvider<SearchState> {
    override val values: Sequence<SearchState> = sequenceOf(
        SearchState()
    )
}