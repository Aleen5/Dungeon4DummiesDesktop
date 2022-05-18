package screens.mainscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.dungeon4dummiesmobile.screens.shared.*
import screens.shared.InputTextField
import screens.shared.MAINCOLOR
import screens.shared.PasswordTextField
import viewModels.UsersViewModel

@Composable
fun LoginScreen() {
    val usersViewModel = UsersViewModel::class.java
    val showDialogLoading = remember {
        mutableStateOf(false)
    }
    var user by remember {
        mutableStateOf("SenatoSenatum15")
    }

    var password by remember {
        mutableStateOf("1234")
    }
    
    Scaffold() {
        Column(
            modifier = Modifier.fillMaxSize().background(color = Color.Black),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
         {
             Card(
                 modifier = Modifier.padding(vertical = 50.dp),
                 backgroundColor = MAINCOLOR
             ) {
                 Column(
                     horizontalAlignment = Alignment.CenterHorizontally,
                 ) {
                     LogoAsset()
                     InputTextField("Username", user, onValueChange = {user = it})
                     PasswordTextField("Password", password, onValueChange = {password = it})
                     Row(
                     ) { }
                 }
             }
        }
    }
}
/*
@Composable
fun LoginButton(user: String, password: String, navController: NavController, usersViewModel: UsersViewModel) {
    val context = LocalContext.current
    val showDialogLoading = remember {
        mutableStateOf(false)
    }
    Button(
        modifier = Modifier
            .width(100.dp)
            .padding(vertical = 15.dp),
        onClick = {

            if (user.equals("") || user.equals(" ")) {
                Toast.makeText(context, "Empty login", Toast.LENGTH_SHORT).show()
                return@Button
            }

            if (password.equals("") || password.equals(" ")) {
                Toast.makeText(context, "Empty password", Toast.LENGTH_SHORT).show()
                return@Button
            }

            showDialogLoading.value = true

            usersViewModel.login(user, password) { currentUser, cause ->
                if (currentUser != null && cause == "good") {
                    navController.navigate(route = AppScreens.HomeScreen.route + "/${currentUser.username}") {
                        popUpTo(0)
                    }
                }
                else if(currentUser == null && cause == "bad"){
                    Toast.makeText(context, "Incorrect login", Toast.LENGTH_SHORT).show()
                    Log.d("XD", "BAD")
                }
                else {
                    Toast.makeText(context, "Unexpected login error", Toast.LENGTH_SHORT).show()
                }
                showDialogLoading.value = false
            }
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = SECONDARYCOLOR)
    ) {
        Text(text = "Log in")
    }
    DialogLoading(showDialogLoading)
}

@Composable
fun NavRegisterButton(navController: NavController) {
    val context = LocalContext.current
    Button(
        modifier = Modifier
            .width(100.dp)
            .padding(vertical = 15.dp),
        onClick = {
            navController.navigate(route = AppScreens.RegisterScreen.route)
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = SECONDARYCOLOR)
    ) {
        Text(text = "Register")
    }
}
*/
