import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import swapplication.CharacterDetailViewModel
import swapplication.StarWarsCharacter

@Composable
fun CharacterDetailScreen(
    characterId: String,
    viewModel: CharacterDetailViewModel = viewModel()
) {
    LaunchedEffect(characterId) {
        viewModel.loadCharacter(characterId)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    if (state.isLoading) {
        CircularProgressIndicator()
    } else if (state.error != null) {
        Text(text = "Ошибка: ${state.error}")
    } else {
        state.character?.let { character ->
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Имя: ${character.name}")
                Text(text = "Рост: ${character.height}")
                Text(text = "Вес: ${character.mass}")
                Text(text = "Пол: ${character.gender}")
            }
        }
    }
}

//@Composable
//fun CharacterInfo(character: StarWarsCharacter) {
//    Column(modifier = Modifier.padding(16.dp)) {
//        Text(text = "Name: ${character.name}", style = MaterialTheme.typography.bodyLarge)
//        Text(text = "Height: ${character.height} cm", style = MaterialTheme.typography.bodyLarge)
//        Text(text = "Mass: ${character.mass} kg", style = MaterialTheme.typography.bodyLarge)
//        Text(text = "Gender: ${character.gender}", style = MaterialTheme.typography.bodyLarge)
//    }
//}