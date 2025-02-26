import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import swapplication.CharacterDetailViewModel
import swapplication.StarWarsCharacter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    characterId: String,
    viewModel: CharacterDetailViewModel = viewModel()
) {
    LaunchedEffect(characterId) {
        viewModel.loadCharacter(characterId)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    if (state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
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
//            TopAppBar(title= { Text("People", fontSize = 22.sp, color = Color.White)},
//                navigationIcon = {
//                    IconButton(onClick = { backDispatcher?.onBackPressed() }) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBack,
//                            tint = Color.White,
//                            contentDescription = "Localized description"
//
//                        )
//                    }
//                },)

            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
//                            Box(modifier = Modifier
//                                .fillMaxWidth()
//                                .background(Color.Black),
//                                contentAlignment = Alignment.Center)
//                            {
//                                Text(
//                                    text = "Character",
//                                    color = Color.White,
//                                    fontSize = 24.sp,
//                                    fontWeight = FontWeight.Bold,
//                                    modifier = Modifier
//                                        .padding(bottom = 16.dp)
//
//                                )
//                            }
                            Text(
                                text = "Character",
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(16.dp)

                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = { backDispatcher?.onBackPressed() }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    tint = Color.White,
                                    contentDescription = "Localized description"

                                )
                            }
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = Color.Black,
                            titleContentColor = Color.White
                        ),
                    )
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CharacterInfo(character, characterId)
                }
            }

            //CharacterInfo(character, characterId)
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
    ){
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
}

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