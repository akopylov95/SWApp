package swapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.StarWarsCharacter
import com.example.domain.usecase.GetCharacterByIdUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class CharacterDetailState(
    val character: StarWarsCharacter? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class CharacterDetailViewModel(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterDetailState())
    val state: StateFlow<CharacterDetailState> = _state

    fun loadCharacter(characterId: String) {
        _state.value = CharacterDetailState(isLoading = true)
        viewModelScope.launch {
            try {
                val character = getCharacterByIdUseCase(characterId)
                Log.i("MyApp", "load character = $character")
                _state.value = CharacterDetailState(character = character, isLoading = false)
            } catch (e: Exception) {
                _state.value = CharacterDetailState(error = e.localizedMessage, isLoading = false)
            }
        }
    }
}

