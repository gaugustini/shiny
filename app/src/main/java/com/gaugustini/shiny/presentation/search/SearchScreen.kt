package com.gaugustini.shiny.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaugustini.shiny.presentation.theme.ShinyTheme
import com.gaugustini.shiny.util.Gender
import com.gaugustini.shiny.util.HunterType

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onSearchClick: () -> Unit = {}
) {
    val state = viewModel.searchState

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        SearchScreenContent(
            state,
            onStateChanged = { viewModel.onSearchStateChanged(it) },
            onSearchClick = onSearchClick
        )
    }

}

@Composable
fun SearchScreenContent(
    state: SearchState,
    onStateChanged: (SearchState) -> Unit = {},
    onSearchClick: () -> Unit = {}
) {
    Column(
        Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        RankChoice(state) { onStateChanged(it) }
        SlotChoice(state) { onStateChanged(it) }
        HunterTypeChoice(state) { onStateChanged(it) }
        GenderChoice(state) { onStateChanged(it) }
        Button(onClick = onSearchClick) {
            Text(text = "Search")
        }
        SkillChoice(state) { onStateChanged(it) }
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
    state.skills.forEach { skill ->
        Row {
            Checkbox(
                checked = state.querySkills.contains(skill),
                onCheckedChange = {
                    if (it) {
                        onStateChanged(state.copy(querySkills = state.querySkills + skill))
                    } else {
                        onStateChanged(state.copy(querySkills = state.querySkills - skill))
                    }
                })
            Text(text = skill)
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