package screens.mainscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import models.UsersModel
import navigation.Screen
import screens.shared.BottomBar
import screens.shared.TopBar
import viewModels.UsersViewModel
import kotlin.random.Random

@Composable
fun HomeScreen(
    onBackPressed:() -> Unit,
    onHomeClick:() -> Unit,
    onCharactersClick:() -> Unit,
    onDiceThrowClick:() -> Unit
) {
    val usersViewModel = UsersViewModel()
    val usersModel = UsersModel
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val username = usersModel.currentUser!!

    val tips = listOf("You don't have to know every rule", "Don't plan too much", "Things aren't written in stone",
        "The rules are only a guide", "Don't attack your Dungeon Master!", "Always keep close your player's handbook",
        "Take your time to write notes", "Always roleplay according to your character's backstory and behavior",
        "Meta-gaming is a sin", "People die if they are killed", "What happens, happens. And there's no turning back.")

    var tipsText by remember {
        mutableStateOf("")
    }

    var getData by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = getData) {
        if (getData) {
            getData = false
            tipsText = tips[Random.nextInt(tips.size)]
        }
    }

    Scaffold(
        topBar = { TopBar("Home", onBackPressed = { onBackPressed() }) },
        bottomBar = { BottomBar(onHomeClick, onCharactersClick, onDiceThrowClick) },
        drawerGesturesEnabled = true,
        scaffoldState = scaffoldState,
        backgroundColor = Color.Black
    ){
        Column(modifier = Modifier.padding(top = 10.dp, end = 10.dp, start = 10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Welcome back, ", color = Color.White)
                Text(text = "$username!", fontSize = 18.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold, color = Color.White)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) { Text(text = "Here's your random D&D tip:", fontSize = 18.sp, color = Color.White) }
            Spacer(modifier = Modifier.height(70.dp))
            Row { Text(
                text = tipsText,
                textAlign = TextAlign.Center,
                fontSize = 34.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.White
            )}

            Spacer(modifier = Modifier.height(30.dp))
            Image(painterResource(resourcePath = "drawable/dungeon4dummieslogotextless.png"), contentDescription = "",
                modifier = Modifier.width(120.dp).height(120.dp))
        }
    }
}