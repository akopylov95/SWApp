package swapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PeopleListViewModel : ViewModel() {
    private val apiService = ApiService()
    private val _state = MutableStateFlow(PeopleListState())
    val state: StateFlow<PeopleListState> = _state

    init {
        loadPeople()
    }

    private fun loadPeople() {
        viewModelScope.launch {
            _state.value = PeopleListState(isLoading = true)
            try {
                val people = apiService.fetchPeopleList()
                _state.update { PeopleListState(people = people.results, isLoading = false) }
            } catch (e: Exception) {
                _state.value = PeopleListState(error = e.localizedMessage, isLoading = false)
            }
        }
    }
}