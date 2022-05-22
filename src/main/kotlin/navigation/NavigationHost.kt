package navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.*
import screens.mainscreens.HomeScreen
import screens.mainscreens.LoginScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavigationHost() {
    var screenState by remember { mutableStateOf<Screen>(Screen.LoginScreen) }
    var username by remember { mutableStateOf("") }

    when (val screen = screenState) {
        is Screen.LoginScreen ->
            LoginScreen(

                onLoginClick = { screenState = Screen.HomeScreen },
            )

        is Screen.HomeScreen ->
            HomeScreen(
                username = username,
            )
    }
}