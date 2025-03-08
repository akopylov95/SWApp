package swapplication.navigation

import swapplication.ui.screen.CharacterDetailScreen
import swapplication.ui.screen.PeopleListScreen
import androidx.compose.runtime.*
import androidx.navigation.compose.*
import org.koin.androidx.compose.koinViewModel
import swapplication.ui.viewmodel.CharacterDetailViewModel
import swapplication.ui.viewmodel.PeopleListViewModel

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "peopleList") {
        composable("peopleList") {
            val viewModel: PeopleListViewModel = koinViewModel()
            PeopleListScreen(viewModel = viewModel) { characterId ->
                navController.navigate("characterDetail/$characterId")

            }
        }

        composable("characterDetail/{characterId}") { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("characterId") ?: return@composable
            val viewModel: CharacterDetailViewModel = koinViewModel()
            CharacterDetailScreen( characterId = characterId, viewModel = viewModel)
        }
    }
}