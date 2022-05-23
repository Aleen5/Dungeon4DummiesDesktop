package screens.mainscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.dungeon4dummiesmobile.screens.shared.*
import models.UsersModel
import screens.shared.InputTextField
import screens.shared.MAINCOLOR
import screens.shared.PasswordTextField
import screens.shared.SECONDARYCOLOR
import viewModels.UsersViewModel

@Composable
fun RegisterScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    val usersViewModel = UsersViewModel()
    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var repeatPassword by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var name by remember {
        mutableStateOf("")
    }

    var surname by remember {
        mutableStateOf("")
    }

    var characters by remember {
        mutableStateOf(mutableListOf(""))
    }

    Scaffold(
        modifier = Modifier.background(Color.Black)

        ) {
        Column(
            modifier = Modifier.fillMaxSize().background(Color.Black),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Card(
                modifier = Modifier.padding(vertical = 50.dp),
                backgroundColor = MAINCOLOR

            ) {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item { LogoAsset()}
                    item { InputTextField("Name", name, onValueChange = {name = it})}
                    item { InputTextField("Surname", surname, onValueChange = {surname = it})}
                    item { InputTextField("E-mail", email, onValueChange = {email = it})}
                    item { InputTextField("Username", username, onValueChange = {username = it})}
                    item { PasswordTextField("Password", password, onValueChange = {password = it})}
                    item { PasswordTextField("Repeat password", repeatPassword, onValueChange = {repeatPassword = it})}
                    item {
                        Row {
                            Button(
                                modifier = Modifier
                                    .width(100.dp)
                                    .padding(vertical = 15.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = SECONDARYCOLOR),
                                onClick = {
                                    // Field checks
                                    if (name == "" || name == " " || name == null) {
                                        return@Button
                                    } else if (surname == "" || surname == " " || surname == null) {
                                        return@Button
                                    } else if (email == "" || email == " " || email == null) {
                                        return@Button
                                    } else if (username == "" || username == " " || username == null) {
                                        return@Button
                                    } else if (password == "" || password == " " || password == null) {
                                        return@Button
                                    } else if (repeatPassword == "" || repeatPassword == " " || repeatPassword == null) {
                                        return@Button
                                    } else if (password != repeatPassword) {
                                        return@Button
                                    }

                                    val rnds = (100000000..999999999).random().toString()
                                    val user = UsersModel(rnds, username, password, name, surname, email, mutableListOf(""))

                                    usersViewModel.get1User(username) { existingUser ->
                                        if (existingUser != null) {
                                            // Mensaje de error

                                        } else {
                                            usersViewModel.postUser(user)
                                            onRegisterClick()

                                        }
                                    }
                                    //navController.navigate(route = AppScreens.HomeScreen.route + "/${currentUser.username}")
                                }
                            ) {
                                Text(text = "Register")
                            }

                            Spacer(modifier = Modifier.width(20.dp))

                            Button(
                                modifier = Modifier
                                    .width(100.dp)
                                    .padding(vertical = 15.dp),
                                onClick = {
                                    onLoginClick()
                                },
                                colors = ButtonDefaults.buttonColors(backgroundColor = SECONDARYCOLOR)
                            ) {
                                Text(text = "Return to Login")
                            }
                        }
                    }
                }
            }
        }
    }
}