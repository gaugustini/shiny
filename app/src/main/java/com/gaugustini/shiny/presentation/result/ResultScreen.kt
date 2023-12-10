package com.gaugustini.shiny.presentation.result

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaugustini.shiny.domain.model.Result
import com.gaugustini.shiny.presentation.theme.ShinyTheme

@Composable
fun ResultScreen(
    viewModel: ResultViewModel = hiltViewModel()
) {
    val state = viewModel.resultState
    ResultScreenContent(state)
}

@Composable
fun ResultScreenContent(state: ResultState) {
    LazyColumn {
        item { LoadingResults(state = state) }
        items(state.results) { result ->
            Text(text = result.head)
            Text(text = result.body)
            Text(text = result.arms)
            Text(text = result.waist)
            Text(text = result.legs)
            result.decorations.forEach { (decoration, amount) ->
                Text(text = "$amount x $decoration")
            }
        }
    }
}

@Composable
fun LoadingResults(state: ResultState) {
    if (state.isLoading) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

@Preview(name = "Light Mode")
//@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SearchScreenPreview(
    @PreviewParameter(ResultScreenPreviewParamProvider::class) state: ResultState
) {
    ShinyTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ResultScreenContent(state)
        }
    }
}

private class ResultScreenPreviewParamProvider : PreviewParameterProvider<ResultState> {
    val result = Result(
        "Head", "Body", "Arms", "Waist", "Legs",
        mapOf("Decoration A" to 1, "Decoration B" to 2, "Decoration C" to 3)
    )

    override val values: Sequence<ResultState> = sequenceOf(
        ResultState(results = listOf(result, result, result, result, result, result))
    )
}