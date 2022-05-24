package screens.mainscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dungeon4dummiesmobile.screens.shared.*
import models.UsersModel
import screens.shared.*
import viewModels.CharactersViewModel
import viewModels.UsersViewModel

@Composable
fun DiceThrowScreen(
    onBackPressed:() -> Unit,
    onHomeClick:() -> Unit,
    onCharactersClick:() -> Unit,
    onDiceThrowClick:() -> Unit
) {
    val usersViewModel = UsersViewModel()
    val usersModel = UsersModel
    val username = usersModel.currentUser
    val charactersViewModel = CharactersViewModel()
    var charactersListSelectedIndex by remember { mutableStateOf(0) }
    var selectedCharacter by remember {
        mutableStateOf(charactersViewModel.charactersModel)
    }

    var user by remember {
        mutableStateOf(usersViewModel.user)
    }

    var charactersList by remember {
        mutableStateOf(charactersViewModel.charactersModelListResponse)
    }

    if (charactersList.size < 1) {
        var firstCharacter = charactersViewModel.charactersModel
        firstCharacter.alias = "Select your character"

        charactersList.add(0, firstCharacter)
    }

    var amount by remember {
        mutableStateOf(0)
    }

    var modifier by remember {
        mutableStateOf(0)
    }

    var totalAmount by remember {
        mutableStateOf(0)
    }

    var getData by remember {
        mutableStateOf(true)
    }

    var hp by remember {
        mutableStateOf(0)
    }

    var maxHP by remember {
        mutableStateOf(0)
    }

    LaunchedEffect(key1 = getData) {
        if (getData) {

            getData = false
            usersViewModel.get1User(username!!) {
                user = it!!
            }
            charactersViewModel.getUsersCharacters(username) { list ->
                charactersList = list.toMutableList()
                var firstCharacter = charactersViewModel.charactersModel
                firstCharacter.alias = "Select your character"

                charactersList.add(0, firstCharacter)
            }
        }
    }

    Scaffold(
        topBar = {
            TopBar("Dice Throw", onBackPressed)
        },
        bottomBar = { BottomBar(onHomeClick, onCharactersClick, onDiceThrowClick) },
        backgroundColor = Color.Black
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(top = 10.dp, end = 10.dp, start = 10.dp).background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Let's throw a die",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        fontFamily = FontFamily.Cursive,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
            }

            // Characters List

            item {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ComposeMenu(charactersList.map { it.alias }, onValueChange = {
                        charactersListSelectedIndex = it
                        selectedCharacter = charactersList[it]
                        hp = selectedCharacter.current_hp
                        maxHP = selectedCharacter.max_hp
                    })
                }

                Spacer(modifier = Modifier.height(5.dp))

                Icon(painter = painterResource(resourcePath = "drawable/shield.png"), "Armor Class",
                    modifier = Modifier.size(35.dp), tint = Color.White)
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "${selectedCharacter.stats.ArmorClass}", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)

                Spacer(modifier = Modifier.height(2.dp))
            }

            item {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(resourcePath = "drawable/heart.png"),
                        "Heart icon",
                        tint = Color.Red,
                        modifier = Modifier.size(35.dp)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "${hp}/${selectedCharacter.max_hp}", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }

                Spacer(modifier = Modifier.height(15.dp))
            }

            item {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            amount = (1..10).random() * 10
                            totalAmount = amount + modifier
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MAINCOLOR),
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Icon(
                            painter = painterResource(
                                resourcePath = "drawable/d100.png"
                            ),
                            contentDescription = "100Die",
                            modifier = Modifier.size(60.dp)
                        )
                    }


                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        onClick = {
                            amount = (1..20).random()
                            totalAmount = amount + modifier
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MAINCOLOR),
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Icon(
                            painter = painterResource(
                                resourcePath = "drawable/d20.png"
                            ),
                            contentDescription = "20Die",
                            modifier = Modifier.size(60.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        onClick = {
                            amount = (1..12).random()
                            totalAmount = amount + modifier
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MAINCOLOR),
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Icon(
                            painter = painterResource(
                                resourcePath = "drawable/d12.png"
                            ),
                            contentDescription = "12Die",
                            modifier = Modifier.size(60.dp)
                        )
                    }
                }


                Spacer(modifier = Modifier.height(10.dp))

            }

            item {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            amount = (1..10).random()
                            totalAmount = amount + modifier
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MAINCOLOR),
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Icon(
                            painter = painterResource(
                                resourcePath = "drawable/d10.png"
                            ),
                            contentDescription = "10Die",
                            modifier = Modifier.size(60.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        onClick = {
                            amount = (1..8).random()
                            totalAmount = amount + modifier
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MAINCOLOR),
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Icon(
                            painter = painterResource(
                                resourcePath = "drawable/d8.png"
                            ),
                            contentDescription = "8Die",
                            modifier = Modifier.size(60.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        onClick = {
                            amount = (1..6).random()
                            totalAmount = amount + modifier
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MAINCOLOR),
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Icon(
                            painter = painterResource(
                                resourcePath = "drawable/d6.png"
                            ),
                            contentDescription = "6Die",
                            modifier = Modifier.size(60.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

            }

            item {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            if (hp - totalAmount < 1)
                                hp = 0
                            else
                                hp -= totalAmount
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Text(text = "Deal", fontWeight = FontWeight.Bold)
                        Icon(painter = painterResource(resourcePath = "drawable/sword.png"), "Damage Sword", modifier = Modifier.size(35.dp))
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        onClick = {
                            amount = (1..4).random()
                            totalAmount = amount + modifier
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MAINCOLOR),
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Icon(
                            painter = painterResource(
                                resourcePath = "drawable/d4.png"
                            ),
                            contentDescription = "4Die",
                            modifier = Modifier.size(60.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        onClick = {
                            if (hp + totalAmount > maxHP)
                                hp = maxHP
                            else
                                hp += totalAmount
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Icon(painter = painterResource(resourcePath = "drawable/heal.png"), "Heal Cross", modifier = Modifier.size(35.dp))
                        Text(text = "Heal", fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))

            }

            item {
                Card(
                    backgroundColor = MAINCOLOR,
                    modifier = Modifier.width(380.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        DisabledReducedNumericInput(
                            label = "Roll",
                            number = amount,
                            onValueChange = { amount = it })
                        ReducedNumericInput(
                            label = "Modifier: (+)",
                            number = modifier,
                            onValueChange = {
                                modifier = it
                                totalAmount = amount + it
                            })
                        DisabledReducedNumericInput(
                            label = "Total Amount",
                            number = totalAmount,
                            onValueChange = { totalAmount = it })
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            item {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = MAINCOLOR),
                    modifier = Modifier.width(400.dp),
                    onClick = {

                        if(charactersListSelectedIndex == 0) {
                            return@Button
                        }

                        selectedCharacter.current_hp = hp
                        selectedCharacter.max_hp = maxHP

                        if (selectedCharacter.current_hp < 1) {
                            if (selectedCharacter.death_saves < 1)
                                selectedCharacter.status = "Dead"
                            else
                                selectedCharacter.status = "Unconscious"
                        } else {
                            selectedCharacter.status = "Alive"
                        }

                        charactersViewModel.updateCharacter(selectedCharacter)
                        // Toast

                    }
                ) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        Text(text = "Save changes", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(85.dp)) }
        }
    }
}