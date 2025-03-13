package com.gaugustini.shiny.presentation.result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaugustini.shiny.domain.model.Solution
import com.gaugustini.shiny.presentation.theme.ShinyTheme

@Composable
fun ResultScreen(
    viewModel: ResultViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {}
) {
    val uiState by viewModel.resultState.collectAsState()

    ResultScreenContent(
        uiState,
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreenContent(
    uiState: ResultState,
    onBackClick: () -> Unit = {},
    onSortClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Results") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = onSortClick) {
                        Icon(Icons.Default.Menu, contentDescription = "Sort")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp)
        ) {
            Text(text = "Found ${uiState.results.size} solutions", fontSize = 16.sp)

            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(uiState.results) {
                    SolutionCard(it)
                }
            }
        }
    }
}

@Composable
fun SolutionCard(solution: Solution) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            SolutionItem(icon = Icons.Default.AccountBox, text = solution.head)
            SolutionItem(icon = Icons.Default.AccountBox, text = solution.body)
            SolutionItem(icon = Icons.Default.AccountBox, text = solution.arms)
            SolutionItem(icon = Icons.Default.AccountBox, text = solution.waist)
            SolutionItem(icon = Icons.Default.AccountBox, text = solution.legs)
            Spacer(modifier = Modifier.height(16.dp))
            solution.decorations.forEach { (decoration, amount) ->
                SolutionItem(icon = Icons.Default.FavoriteBorder, text = "$decoration x $amount")
            }
        }
    }
}

@Composable
fun SolutionItem(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = icon, contentDescription = null)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, fontSize = 16.sp)
    }
}

@Preview(name = "Light Mode")
//@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ResultScreenPreview(
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
    val solution = Solution(
        "Head", "Body", "Arms", "Waist", "Legs",
        mapOf("Decoration A" to 1, "Decoration B" to 2, "Decoration C" to 3)
    )

    override val values: Sequence<ResultState> = sequenceOf(
        ResultState(results = listOf(solution, solution, solution, solution, solution, solution))
    )
}
