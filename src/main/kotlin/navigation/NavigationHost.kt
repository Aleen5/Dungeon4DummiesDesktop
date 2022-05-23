package navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.*
import screens.mainscreens.HomeScreen
import screens.mainscreens.LoginScreen
import screens.mainscreens.RegisterScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavigationHost() {
    var screenState by remember { mutableStateOf<Screen>(Screen.LoginScreen) }
    var username by remember { mutableStateOf("") }

    when (val screen = screenState) {
        is Screen.LoginScreen ->
            LoginScreen(
                onLoginClick = { screenState = Screen.HomeScreen },
                onRegisterClick = { screenState = Screen.RegisterScreen },
            )

        is Screen.RegisterScreen ->
            RegisterScreen(
                onRegisterClick = { screenState = Screen.HomeScreen },
                onLoginClick = { screenState = Screen.LoginScreen }
            )

        is Screen.HomeScreen ->
            HomeScreen(
                username = username,
            )
    }
}