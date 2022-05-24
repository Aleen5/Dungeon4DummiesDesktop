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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import models.Auth
import models.UsersModel
import screens.shared.InputTextField
import screens.shared.MAINCOLOR
import screens.shared.PasswordTextField
import screens.shared.SECONDARYCOLOR
import viewModels.UsersViewModel

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    val usersViewModel = UsersViewModel()
    val usersModel = UsersModel
    val showDialogLoading = remember {
        mutableStateOf(false)
    }
    var user by remember {
        mutableStateOf("SenatoSenatum15")
    }

    var password by remember {
        mutableStateOf("1234")
    }

    usersModel.currentUser = null
    
    Scaffold(
        modifier = Modifier.background(Color.Black)
    ) {
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
                     ) {
                         Button(
                             modifier = Modifier
                                 .width(100.dp)
                                 .padding(vertical = 15.dp),
                             onClick = {
                                 if (user == "" || user == " ") {
                                     return@Button
                                 }

                                 if (password == "" || password == " ") return@Button

                                 val auth = Auth(user, password)
                                 usersViewModel.login(auth) { currentUser, cause ->
                                     if (currentUser != null && cause == "good") {
                                        usersModel.currentUser = currentUser.username
                                        onLoginClick()
                                     }
                                 }
                             },
                             colors = ButtonDefaults.buttonColors(backgroundColor = SECONDARYCOLOR)
                         ) {
                             Text(text = "Log in")
                         }
                         Spacer(modifier = Modifier.width(40.dp))

                         // BOTON REGISTER

                         Button(
                             modifier = Modifier
                                 .width(100.dp)
                                 .padding(vertical = 15.dp),
                             onClick = {
                                onRegisterClick()
                             },
                             colors = ButtonDefaults.buttonColors(backgroundColor = SECONDARYCOLOR)
                         ) {
                             Text(text = "Register")
                         }
                     }
                 }
             }
        }
    }
}

@Composable
fun NavRegisterButton() {

}

