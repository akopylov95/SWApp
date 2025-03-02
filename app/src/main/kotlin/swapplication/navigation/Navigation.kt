package swapplication.navigation

import swapplication.ui.screen.CharacterDetailScreen
import swapplication.ui.screen.PeopleListScreen
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import swapplication.ui.viewmodel.CharacterDetailViewModel

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "peopleList") {
        Log.i("MyLog111", "Попали в navigation")
        composable("peopleList") {
            Log.i("MyLog111", "Попали в пипл лист скрин")
            PeopleListScreen { characterId ->
                Log.i("MyLog111", "Получили ИД: ${characterId}" )
                navController.navigate("characterDetail/$characterId")
                Log.i("MyApp composable(ID)", characterId )

            }
        }

        composable("characterDetail/{characterId}") { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("characterId") ?: return@composable
            val viewModel: CharacterDetailViewModel = viewModel()
            CharacterDetailScreen( characterId = characterId, viewModel = viewModel)
        }
    }
}