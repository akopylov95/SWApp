package swapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val apiService: ApiService) : ViewModel() {
    private val _state = MutableStateFlow(MainState()) // Внутренний поток состояния
    val state: StateFlow<MainState> = _state.asStateFlow() // Открытый поток для UI

    fun processIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.LoadCharacter -> fetchCharacter()
        }
    }

    private fun fetchCharacter() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true) // Включаем индикатор загрузки
            try {
                val character = apiService.fetchCharacter() // Загружаем данные
                _state.value = MainState(character = character, isLoading = false) // Успешный результат
            } catch (e: Exception) {
                _state.value = MainState(error = e.localizedMessage, isLoading = false) // Ошибка
            }
        }
    }
}