package screens.shared

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun BottomBar(onHomeClick:() -> Unit, onCharactersClick:() -> Unit, onDiceThrowClick:() -> Unit) {
    BottomAppBar(
        backgroundColor = Color.DarkGray
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            IconButton(
                onClick = {
                    onHomeClick()
                }
            ) {
                Icon(Icons.Filled.Home, "Home")
            }

            Spacer(modifier = Modifier.width(25.dp))

            IconButton(
                onClick = {
                    onCharactersClick()
                }
            ) {
                Icon(Icons.Filled.Person, "Characters")

            }

            Spacer(modifier = Modifier.width(25.dp))

            IconButton(
                onClick = {
                    onDiceThrowClick()
                }
            ) {
                Icon(Icons.Filled.PlayArrow, "DiceThrow")
            }
        }

    }
}


/*
@Composable
fun BottomBar(navController: NavController, username: String) {
    val items = listOf(
        BottomBarNavigation.Home,
        BottomBarNavigation.Characters,
        BottomBarNavigation.DiceThrow
    )
    
    BottomNavigation(
        backgroundColor = Color.Black,
        contentColor = Color.White,
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = item.title)},
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = false,
                onClick = {
                    navController.navigate(item.route + "/${username}") {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
*/