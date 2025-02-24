package swapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class CharacterDetailState(
    val character: StarWarsCharacter? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class CharacterDetailViewModel : ViewModel() {
    private val apiService = ApiService()
    private val _state = MutableStateFlow(CharacterDetailState())
    val state: StateFlow<CharacterDetailState> = _state

    fun loadCharacter(id: String) {
        viewModelScope.launch {
            _state.value = CharacterDetailState(isLoading = true)
            try {
                val character = apiService.fetchCharacterById(id)
                Log.i("MyApp", "load character = $character")
                _state.value = CharacterDetailState(character = character, isLoading = false)
            } catch (e: Exception) {
                _state.value = CharacterDetailState(error = e.localizedMessage, isLoading = false)
            }
        }
    }
}