package screens.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants

@Composable
fun TopBar(barText: String, onBackPressed:() -> Unit) {
    TopAppBar(
        backgroundColor = MAINCOLOR,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    IconButton(onClick = {
                        onBackPressed()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "",)
                    }
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(end = 45.dp).fillMaxWidth().align(Alignment.CenterHorizontally),
                        text = barText,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontSize = 25.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    )
}


@Composable
fun TopBarExtended(barText: String, onBackPressed:() -> Unit, visibility: Boolean, visibilityIconVisible: Boolean, onVisibilityClick:(visible: Boolean) -> Unit) {
    TopAppBar(
        backgroundColor = MAINCOLOR,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    IconButton(onClick = {
                        onBackPressed()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "",)
                    }
                }

                Spacer(modifier = Modifier.width(25.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(end = 45.dp).wrapContentSize().align(Alignment.CenterHorizontally),
                        text = barText,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontSize = 25.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                    )
                }

                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.fillMaxWidth().padding(end = 30.dp),

                ) {
                    if (visibility && visibilityIconVisible) Icon(
                        painter = painterResource(resourcePath = "drawable/eye.png"),
                        "",
                        tint = Color.LightGray,
                        modifier = Modifier.size(20.dp).clickable {
                            onVisibilityClick(false)
                        }
                    ) else if (!visibility && visibilityIconVisible) Icon(
                        painter = painterResource(resourcePath = "drawable/eyeoff.png"),
                        "",
                        tint = Color.LightGray,
                        modifier = Modifier.size(20.dp).clickable {
                            onVisibilityClick(true)
                        }
                    )
                }
            }
        }
    )
}

/*

@Composable
fun TopBarExtendedWithVisibility(barText: String, scope: CoroutineScope, scaffoldState: ScaffoldState, visibility: Boolean, visibilityIconVisible: Boolean, onVisibilityClick:(visible: Boolean) -> Unit) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Column() {
                    Text(
                        text = barText,
                        fontSize = 25.sp,
                        color = Color.Black,
                        fontFamily = FontFamily.Cursive,
                        fontWeight = FontWeight.Bold
                    )
                }

                Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth().padding(end = 30.dp)) {
                    if (visibility && visibilityIconVisible) Icon(
                        painter = painterResource(id = R.drawable.eye),
                        "",
                        tint = Color.LightGray,
                        modifier = Modifier.size(20.dp).clickable {
                            onVisibilityClick(false)
                        }
                    ) else if (!visibility && visibilityIconVisible) Icon(
                        painter = painterResource(id = R.drawable.eyeoff),
                        "",
                        tint = Color.LightGray,
                        modifier = Modifier.size(20.dp).clickable {
                            onVisibilityClick(true)
                        }
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(Icons.Filled.Menu, "")
            }
        },
        backgroundColor = colorResource(id = R.color.MAINCOLOR),
        contentColor = Color.White
    )
}


@Composable
fun Drawer(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavController, username: String) {
    val items = listOf(
        DrawerNavigation.Profile,
        DrawerNavigation.Settings,
        DrawerNavigation.Log_Out
    )
    Column (
        modifier = Modifier.background(colorResource(id = R.color.MAINCOLOR))
    ) {
        Image(
            painter = painterResource(id = R.drawable.dungeon4dummieslogo),
            contentDescription = R.drawable.dungeon4dummieslogo.toString(),
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .padding(10.dp)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            DrawerItem(item = item, selected = currentRoute == item.route, onItemClick = {
                if (item.title == "Log out")
                    navController.navigate(item.route)
                else {
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

                scope.launch {
                    scaffoldState.drawerState.close()
                }
            })
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Dungeons4Dummies v. 0.5",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun DrawerItem(item: DrawerNavigation, selected: Boolean, onItemClick: (DrawerNavigation) -> Unit) {
    val background = if (selected) MAINCOLOR else Color.Transparent
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClick(item) })
            .height(45.dp)
            .background(colorResource(id = background))
            .padding(start = 10.dp)
    ) {
        Image(
            imageVector = item.icon,
            contentDescription = item.title,
            colorFilter = ColorFilter.tint(Color.White),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(35.dp)
                .width(35.dp)
        )
        Spacer(modifier = Modifier.width(7.dp))
        Text(
            text = item.title,
            fontSize = 18.sp,
            color = Color.White
        )
    }
}
*/