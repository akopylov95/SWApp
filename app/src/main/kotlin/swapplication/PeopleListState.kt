package swapplication

data class PeopleListState(
    val people: List<StarWarsCharacter> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)