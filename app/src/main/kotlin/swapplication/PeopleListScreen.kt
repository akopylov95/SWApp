import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import swapplication.PeopleListViewModel
import androidx.compose.ui.Modifier


@Composable
fun PeopleListScreen(
    viewModel: PeopleListViewModel = viewModel(),
    onCharacterClick: (String) -> Unit
) {

    val state by viewModel.state.collectAsState()

    if (state.isLoading) {
        CircularProgressIndicator()
    } else if (state.error != null) {
        Text(text = "Ошибка: ${state.error}")
    } else {
        LazyColumn {
            items(state.people) { character ->
                Text(
                    text = character.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            val characterId = character.url.split("/").filter { it.isNotEmpty() }.last() //  Получаем ID из URL
                            onCharacterClick(characterId)
                        }
                        .padding(16.dp)
                )
            }
        }
    }
}