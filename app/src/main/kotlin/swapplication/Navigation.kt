package swapplication

import CharacterDetailScreen
import PeopleListScreen
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import io.ktor.client.call.body
import io.ktor.client.request.get

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "peopleList") {
        composable("peopleList") {
            PeopleListScreen { characterId ->
                navController.navigate("characterDetail/$characterId")
                Log.i("MyApp composable(ID)", characterId )
            }
        }

        composable("characterDetail/{characterId}") { backStackEntry ->
            Log.i("MyApp composable(\"{characterId}\")", backStackEntry.arguments?.getString("characterId").orEmpty())
            val characterId = backStackEntry.arguments?.getString("characterId") ?: return@composable
            val viewModel: CharacterDetailViewModel = viewModel()
            CharacterDetailScreen( characterId = characterId, viewModel = viewModel)
        }
    }
}