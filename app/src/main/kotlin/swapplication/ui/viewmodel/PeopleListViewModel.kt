package swapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.StarWarsCharacter
import com.example.domain.usecase.GetPeopleUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class PeopleListState(
    val people: List<StarWarsCharacter> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class PeopleListViewModel (
    private val getPeopleUseCase: GetPeopleUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(PeopleListState())
    val state: StateFlow<PeopleListState> = _state

    init {
        loadPeople()
    }

    private fun loadPeople() {
        viewModelScope.launch {
            _state.value = PeopleListState(isLoading = true)
            viewModelScope.launch {
                try {
                    val people = getPeopleUseCase()
                    _state.value = PeopleListState(people = people)
                } catch (e: Exception) {
                    _state.value = PeopleListState(error = e.localizedMessage)
                }
            }
        }
    }
}

