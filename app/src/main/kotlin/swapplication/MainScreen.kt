package swapplication

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.processIntent(MainIntent.LoadCharacter)
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Star Wars Character") }) }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when {
                state.isLoading -> CircularProgressIndicator()
                state.error != null -> Text("Error: ${state.error}")
                state.character != null -> CharacterInfo(state.character!!)
            }
        }
    }
}

@Composable
fun CharacterInfo(character: StarWarsCharacter) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Name: ${character.name}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Height: ${character.height} cm", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Mass: ${character.mass} kg", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Gender: ${character.gender}", style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val mockCharacter = StarWarsCharacter(
        name = "Luke Skywalker",
        height = "172",
        mass = "77",
        gender = "Male"
    )

    val mockState = MainState(character = mockCharacter, isLoading = false, error = null)

    MainScreenPreviewContent(mockState)
}

@Composable
fun MainScreenPreviewContent(state: MainState) {
    when {
        state.isLoading -> CircularProgressIndicator()
        state.character != null -> CharacterInfo(state.character!!)
        state.error != null -> Text("Error: ${state.error}")
    }
}