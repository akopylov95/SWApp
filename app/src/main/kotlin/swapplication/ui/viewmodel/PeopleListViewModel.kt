package swapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.data.entity.StarWarsCharacter
import com.example.domain.usecase.EntityMapperUseCaseImpl
import com.example.domain.usecase.EntityMapperUseCaseInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PeopleListViewModel : ViewModel() {
    private val entityMapperUseCase: EntityMapperUseCaseInterface = EntityMapperUseCaseImpl() // TODO we need to do it with di (Koin/Dagger2)
//    private val apiService = ApiService()
    private val _state = MutableStateFlow(PeopleListState())
    val state: StateFlow<PeopleListState> = _state

    init {
        loadPeople()
    }

    private fun loadPeople() {
        viewModelScope.launch {
            _state.update { PeopleListState(isLoading = true) }
            try {
                val people = entityMapperUseCase.fetchPeopleList()
                _state.update { PeopleListState(people = people.results, isLoading = false) }
            } catch (e: Exception) {
                _state.update { PeopleListState(error = e.localizedMessage, isLoading = false) }
            }
        }
    }
}

data class PeopleListState(
    val people: List<StarWarsCharacter> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)