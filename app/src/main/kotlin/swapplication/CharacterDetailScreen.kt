import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import swapplication.CharacterDetailViewModel
import swapplication.StarWarsCharacter

@Composable
fun CharacterDetailScreen(
    characterId: String,
    viewModel: CharacterDetailViewModel = viewModel()
) {
    LaunchedEffect(characterId) {
        viewModel.loadCharacter(characterId)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    if (state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ){
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
            ){
                CircularProgressIndicator()
            }
        }
    } else if (state.error != null) {
        Text(text = "Ошибка: ${state.error}")
    } else {
        state.character?.let { character ->
            CharacterInfo(character, characterId)
        }
    }
}

@Composable
fun CharacterInfo(character: StarWarsCharacter, characterId: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0x33767676)),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = character.name,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            AssetImage(
                "$characterId.jpg",
                modifier = Modifier
                    .height(520.dp)
                    .width(360.dp)
                    //.size(360.dp)
                    .clip(RoundedCornerShape(8.dp)),
                )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Appearance", color = Color.Gray, fontSize = 16.sp)

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                InfoRow("Height", character.height)
                InfoRow("Mass", character.mass)
                InfoRow("Gender", character.gender)
            }
        }
    }
}

//@Composable
//fun CharacterInfo(character: StarWarsCharacter, characterId: String) {
//    Column(modifier = Modifier.padding(16.dp)) {
//        AssetImage("$characterId.jpg")
//        InfoRow("Name", character.name)
//        Text(text = "Name: ${character.name}", style = MaterialTheme.typography.bodyLarge)
//        Text(text = "Height: ${character.height} cm", style = MaterialTheme.typography.bodyLarge)
//        Text(text = "Mass: ${character.mass} kg", style = MaterialTheme.typography.bodyLarge)
//        Text(text = "Gender: ${character.gender}", style = MaterialTheme.typography.bodyLarge)
//    }
//}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = Color.White, fontSize = 18.sp)
        Text(text = value, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
}