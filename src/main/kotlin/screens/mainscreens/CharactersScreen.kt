package screens.mainscreens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import models.CharactersModel
import models.UsersModel
import org.intellij.lang.annotations.JdkConstants
import scala.collection.script.End
import screens.shared.BottomBar
import screens.shared.MAINCOLOR
import screens.shared.SECONDARYCOLOR
import screens.shared.TopBar
import viewModels.CharactersViewModel

@Composable
fun CharactersScreen(
    onBackPressed:() -> Unit,
    onHomeClick:() -> Unit,
    onCharactersClick:() -> Unit,
    onDiceThrowClick:() -> Unit,
    onCharacterClick: () -> Unit,
    onCreateButtonPressed: () -> Unit
) {
    val charactersViewModel = CharactersViewModel()
    val usersModel = UsersModel
    val username = usersModel.currentUser!!

    var visibility by remember {
        mutableStateOf(true)
    }

    var charactersList by remember {
        mutableStateOf(charactersViewModel.charactersModelListResponse)
    }

    var getData by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = getData) {
        if (getData) {
            getData = false
            charactersViewModel.getUsersCharacters(username) {
                charactersList = it as MutableList<CharactersModel>
            }
        }
    }

    Scaffold(
        topBar = { TopBar("Characters", onBackPressed) },
        bottomBar = { BottomBar(onCharactersClick = onCharactersClick, onHomeClick = onHomeClick, onDiceThrowClick = onDiceThrowClick) },
        modifier = Modifier.background(Color.Black)
    ) {
        Spacer(modifier = Modifier.padding(top = 40.dp))
        if (charactersList.isNotEmpty()) {

            LazyColumn(
                modifier = Modifier.fillMaxWidth().background(Color.Black),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(charactersList) { character ->
                    CharacterCard(
                        onCardClicked = onCharacterClick,
                        character = character,
                        visibility = visibility,
                    )
                }
                item { CreateCharacterScreenButton(username = username, onCreateButtonPressed = onCreateButtonPressed) }
                item { Spacer(modifier = Modifier.height(60.dp)) }
            }
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxHeight().background(Color.Black)) {
                Text(text = "Nothing to show here. Maybe create a new character? ${username} ${charactersList.size}", color = Color.White, modifier = Modifier.padding(top = 40.dp))
                CreateCharacterScreenButton(username = username, onCreateButtonPressed = onCreateButtonPressed)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharacterCard(onCardClicked:() -> Unit, character: CharactersModel,
                  visibility: Boolean,
                  characterSP: CharactersViewModel = CharactersViewModel()) {

    val spoilerCharacter = characterSP.charactersSpoilerModel
    val usersModel = UsersModel

    Card(
        backgroundColor = MAINCOLOR,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 18.dp)
            .combinedClickable(
                onClick = {
                    usersModel.selectedCharacterId = character.id
                    onCardClicked()
                },
                onLongClick = {

                }
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,

            ) {
            if (visibility)
                Image(
                    painter = painterResource(resourcePath = "drawable/dungeon4dummieslogotextless.png"),
                    "Character_Avatar",
                    modifier = Modifier
                        .size(60.dp)
                        .padding(7.dp))
            else
                Image(
                    painter = painterResource(resourcePath = "drawable/spoileravatar.png"),
                    "Character_Avatar",
                    modifier = Modifier
                        .size(60.dp)
                        .padding(7.dp)
                        .clip(CircleShape))

            Column {
                Row() { Text(character.alias, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 5.dp)) }
                Row() {
                    if (visibility)
                        Text("Lvl ${character.level}")
                    else
                        Text("Lvl ???")
                    Spacer(modifier = Modifier.width(15.dp))
                    ClassIcon(characterClass = character.character_class)
                    Spacer(modifier = Modifier.width(5.dp))
                    Text("${character.character_class} ${character.archetype}")
                }
                Row() {
                    Text(text = "Status: ")
                    if (visibility) {
                        if (character.status.lowercase() == "alive") {
                            Text(
                                character.status,
                                color = Color.Green,
                                fontStyle = FontStyle.Italic
                            )
                        } else if (character.status.lowercase() == "dead") {
                            Text(
                                character.status,
                                color = Color.Red,
                                fontStyle = FontStyle.Italic
                            )
                        } else {
                            Text(
                                character.status,
                                color = Color.Yellow,
                                fontStyle = FontStyle.Italic
                            )
                        }
                    } else
                        Text(
                            spoilerCharacter.status,
                            color = Color.Yellow,
                            fontStyle = FontStyle.Italic
                        )
                }
                Row() {
                    Icon(painterResource(resourcePath = "drawable/heart.png"), "HP", tint = Color.Red,
                    modifier = Modifier.size(15.dp))
                    Spacer(modifier = Modifier.width(5.dp))

                    if (visibility) {
                        Text(
                            "HP: ${character.current_hp}/${character.max_hp}",
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(painterResource(resourcePath = "drawable/flask.png"), "MP", tint = Color.Blue,
                            modifier = Modifier.size(15.dp))
                        Spacer(modifier = Modifier.width(5.dp))
                        Text("Mana: ${character.current_mana}/${character.max_mana}",
                            modifier = Modifier.padding(bottom = 5.dp))
                    }
                    else {
                        Text(
                            "HP: ???/???",
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(painterResource(resourcePath = "drawable/flask.png"), "MP", tint = Color.Blue,
                            modifier = Modifier.size(15.dp))
                        Spacer(modifier = Modifier.width(5.dp))
                        Text("Mana: ???/???",
                            modifier = Modifier.padding(bottom = 5.dp))
                    }
                }
            }

            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.fillMaxWidth().padding(end = 10.dp)
            ) {
                Image(
                    painter = painterResource(resourcePath = "drawable/dandd.png"), "Dungeons&Dragons Logo"
                )
            }
        }
    }
}

@Composable
fun CreateCharacterScreenButton(username: String, onCreateButtonPressed:() -> Unit) {
    Button(onClick = {
        onCreateButtonPressed()
    }) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Icon(painterResource(resourcePath = "drawable/plus.png"), "", modifier = Modifier
                    .height(25.dp)
                    .width(25.dp))
                Spacer(modifier = Modifier.width(10.dp))
                Text("Create a new character",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ClassIcon(characterClass: String) {
    var painter = painterResource(resourcePath = "drawable/list.png")

    when(characterClass.toLowerCase()) {
        "warrior" -> painter = painterResource(resourcePath = "drawable/sword.png")
        "barbarian" -> painter = painterResource(resourcePath = "drawable/axe.png")
        "bard" -> painter = painterResource(resourcePath = "drawable/guitar.png")
        "cleric" -> painter = painterResource(resourcePath = "drawable/cross.png")
        "druid" -> painter = painterResource(resourcePath = "drawable/cat.png")
        "fighter" -> painter = painterResource(resourcePath = "drawable/karate.png")
        "monk" -> painter = painterResource(resourcePath = "drawable/temple.png")
        "paladin" -> painter = painterResource(resourcePath = "drawable/mace.png")
        "rogue" -> painter = painterResource(resourcePath = "drawable/knife.png")
        "sorcerer" -> painter = painterResource(resourcePath = "drawable/staff.png")
        "warlock" -> painter = painterResource(resourcePath = "drawable/crystalball.png")
        "wizard" -> painter = painterResource(resourcePath = "drawable/wizardhat.png")
        "artificer" -> painter = painterResource(resourcePath = "drawable/bomb.png")
        "blood hunter" -> painter = painterResource(resourcePath = "drawable/sword.png")
        "ranger" -> painter = painterResource(resourcePath = "drawable/bow.png")
        else -> painterResource(resourcePath = "drawable/list.png")
    }
    Icon(painter = painter, "", tint = Color.LightGray,
        modifier = Modifier.size(15.dp))
}