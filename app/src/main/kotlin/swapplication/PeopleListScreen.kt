import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import swapplication.PeopleListViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.swapp.R
import swapplication.StarWarsCharacter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeopleListScreen(
    viewModel: PeopleListViewModel = viewModel(),
    onCharacterClick: (String) -> Unit
) {

    val state by viewModel.state.collectAsState()

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
//            Text(
//                text = "People",
//                color = Color.White,
//                fontSize = 24.sp,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.padding(bottom = 16.dp)
//            )

        val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
//                        Box(modifier = Modifier
//                            .fillMaxWidth()
//                            .background(Color.Black),
//                            contentAlignment = Alignment.Center) {
//                            Text(
//                                text = "People",
//                                color = Color.White,
//                                fontSize = 24.sp,
//                                fontWeight = FontWeight.Bold,
//                                modifier = Modifier
//                                    .padding(bottom = 16.dp)
//
//                            )
//                        }
                        Text(
                                text = "People",
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(16.dp)

                            )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Black,
                        titleContentColor = Color.White
                    ),
                    scrollBehavior = scrollBehavior
                )
            }
        ){innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyColumn (
                    contentPadding = innerPadding,
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                ){
                    items(state.people) { character ->
                        val characterId = character.url.split("/").filter { it.isNotEmpty() }.last() //  Получаем ID из URL
                        PeopleInfo(character, characterId, onCharacterClick)
                    }
                }
            }
        }

    }
}

@Composable
fun AssetImage(assetName: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val imageUri = "file:///android_asset/$assetName"

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(imageUri) // Путь к картинке в assets
            .crossfade(true) // Плавное появление
            .build(),
        contentDescription = "Картинка из assets",
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}

@Composable
fun PeopleInfo(
    character: StarWarsCharacter,
    characterId: String,
    onCharacterClick: (String) -> Unit
) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0x33767676)
        )
    ){
        Row (
            modifier = Modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AssetImage("$characterId.jpg",
                Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp)),
            )

            Spacer(modifier = Modifier
                .width(16.dp)
            )

            Text(
                modifier = Modifier
                    .clickable {
                        onCharacterClick(characterId)
                    },
                text = character.name,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily(Font(R.font.inter18))
            )
        }
    }
}
