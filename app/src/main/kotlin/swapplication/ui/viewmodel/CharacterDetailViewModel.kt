package swapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.data.entity.StarWarsCharacter
import com.example.domain.usecase.EntityMapperUseCaseImpl
import com.example.domain.usecase.EntityMapperUseCaseInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterDetailViewModel : ViewModel() {

    private val entityMapperUseCase: EntityMapperUseCaseInterface = EntityMapperUseCaseImpl() // TODO we need to do it with di (Koin/Dagger2)
//    private val apiService = ApiService()
    private val _state = MutableStateFlow(CharacterDetailState())
    val state: StateFlow<CharacterDetailState> = _state

    fun loadCharacter(id: String) {
        viewModelScope.launch {
            _state.value = CharacterDetailState(isLoading = true)
            try {
                val character = entityMapperUseCase.fetchCharacterById(id)
                Log.i("MyApp", "load character = $character")
                _state.value = CharacterDetailState(character = character, isLoading = false)
            } catch (e: Exception) {
                _state.value = CharacterDetailState(error = e.localizedMessage, isLoading = false)
            }
        }
    }
}

data class CharacterDetailState(
    val character: StarWarsCharacter? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)