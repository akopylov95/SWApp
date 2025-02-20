package swapplication

data class MainState(
    val character: StarWarsCharacter? = null, // Загруженный персонаж
    val isLoading: Boolean = false, // Флаг загрузки
    val error: String? = null // Ошибка при загрузке
)
