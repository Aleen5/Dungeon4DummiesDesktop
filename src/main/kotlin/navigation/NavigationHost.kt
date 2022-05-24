package navigation

import androidx.compose.runtime.*
import screens.mainscreens.*

@Composable
fun NavigationHost() {
    var screenState by remember { mutableStateOf<Screen>(Screen.LoginScreen) }

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
                onBackPressed = { screenState = Screen.LoginScreen },
                onHomeClick = { screenState = Screen.HomeScreen },
                onCharactersClick = { screenState = Screen.CharactersScreen },
                onDiceThrowClick = { screenState = Screen.DiceThrowScreen }

            )

        is Screen.CharactersScreen ->
            CharactersScreen(
                onBackPressed = { screenState = Screen.LoginScreen },
                onHomeClick = { screenState = Screen.HomeScreen },
                onCharactersClick = { screenState = Screen.CharactersScreen },
                onDiceThrowClick = { screenState = Screen.DiceThrowScreen },
                onCharacterClick = { screenState = Screen.CharacterScreen },
                onCreateButtonPressed = { screenState = Screen.CharacterCreationScreen }
            )
        is Screen.CharacterScreen ->
            CharacterScreen(
                onBackPressed = { screenState = Screen.CharactersScreen },
                onHomeClick = { screenState = Screen.HomeScreen },
                onCharactersClick = { screenState = Screen.CharactersScreen },
                onDiceThrowClick = { screenState = Screen.DiceThrowScreen },
            )

        is Screen.CharacterCreationScreen ->
            CharacterCreationScreen(
                onBackPressed = { screenState = Screen.CharactersScreen },
                onHomeClick = { screenState = Screen.HomeScreen },
                onCharactersClick = { screenState = Screen.CharactersScreen },
                onDiceThrowClick = { screenState = Screen.DiceThrowScreen },
            )

        is Screen.DiceThrowScreen ->
            DiceThrowScreen(
                onBackPressed = { screenState = Screen.LoginScreen },
                onHomeClick = { screenState = Screen.HomeScreen },
                onCharactersClick = { screenState = Screen.CharactersScreen },
                onDiceThrowClick = { screenState = Screen.DiceThrowScreen },
            )
    }
}