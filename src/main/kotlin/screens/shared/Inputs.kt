package screens.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import screens.mainscreens.ClassIcon

@Composable
fun InputTextField(label: String, inValue: String, onValueChange:(textUser: String) -> Unit) {
    TextField(
        value = inValue,
        onValueChange = { onValueChange(it) },
        label = { Text(label) },
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun LongInputTextField(label: String, inValue: String, onValueChange:(textUser: String) -> Unit) {
    TextField(
        value = inValue,
        onValueChange = { onValueChange(it) },
        label = { Text(label) },
        modifier = Modifier
            .padding(16.dp)
            .height(120.dp)
    )
}

@Composable
fun PasswordTextField(label: String, password: String, onValueChange:(textPassword: String) -> Unit) {
    var passwordVisible by rememberSaveable{ mutableStateOf(false) }

    TextField(
        value = password,
        onValueChange = { onValueChange(it) },
        label = { Text(label) },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier.padding(16.dp),
        trailingIcon = {
            val image = if (passwordVisible)
                painterResource(resourcePath = "drawable/eyeoff.png")
            else painterResource(resourcePath = "drawable/eye.png")

            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(painter = image, contentDescription = description, tint = Color.LightGray, modifier = Modifier.size(25.dp))
            }
        }
    )
}

@Composable
fun NumericInput(label: String, number: Int, onValueChange:(intValue: Int) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            TextField(
                value = number.toString(),
                onValueChange = { value ->
                    if (value == "")
                        onValueChange(0)
                    else if (value.length <= 2) {
                        val text = value.filter { it.isDigit() }
                        onValueChange(Integer.parseInt(text))
                    }
                },
                label = { Text(label, textAlign = TextAlign.Center, fontSize = 10.sp, color = Color.White) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .padding(16.dp)
                    .width(100.dp)
                    .height(60.dp)
            )
        }
    }
}

@Composable
fun DisabledNumericInput(label: String, number: Int, onValueChange:(intValue: Int) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            TextField(
                value = number.toString(),
                onValueChange = { value ->
                    if (value == "")
                        onValueChange(0)
                    else if (value.length <= 2) {
                        val text = value.filter { it.isDigit() }
                        onValueChange(Integer.parseInt(text))
                    }
                },
                enabled = false,
                label = { Text(label, textAlign = TextAlign.Center, fontSize = 10.sp) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .padding(16.dp)
                    .width(100.dp)
                    .height(60.dp)
            )
        }
    }
}

@Composable
fun ReducedNumericInput(label: String, number: Int, onValueChange:(intValue: Int) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            TextField(
                value = number.toString(),
                onValueChange = { value ->
                    if (value == "")
                        onValueChange(0)
                    else if (value.length <= 2) {
                        val text = value.filter { it.isDigit() }
                        onValueChange(Integer.parseInt(text))
                    }
                },
                label = { Text(label, textAlign = TextAlign.Center, fontSize = 10.sp, color = Color.White) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .padding(10.dp)
                    .width(100.dp)
                    .height(60.dp)
            )
        }
    }
}

@Composable
fun DisabledReducedNumericInput(label: String, number: Int, onValueChange:(intValue: Int) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            TextField(
                value = number.toString(),
                onValueChange = { value ->
                    if (value == "")
                        onValueChange(0)
                    else if (value.length <= 2) {
                        val text = value.filter { it.isDigit() }
                        onValueChange(Integer.parseInt(text))
                    }
                },
                enabled = false,
                label = { Text(label, textAlign = TextAlign.Center, fontSize = 10.sp) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .padding(10.dp)
                    .width(100.dp)
                    .height(60.dp)
            )
        }
    }
}



@Composable
fun ComposeMenu(list:List<String>, onValueChange:(selectedIndex: Int) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val disabledValue = "B"
    var selectedIndex by remember { mutableStateOf(0) }
    Card(modifier = Modifier.width(250.dp).wrapContentSize(Alignment.TopStart)) {
        Text(list[selectedIndex], textAlign = TextAlign.Center, fontSize = 15.sp, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth().clickable(onClick = { expanded = true }).background(
            Color.Gray))
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth().background(MAINCOLOR)
        ) {
            list.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                    onValueChange(index)
                }) {
                    val disabledText = if (s == disabledValue) {
                        " (Disabled)"
                    } else {
                        ""
                    }
                    ClassIcon(list[index])
                    Spacer(modifier = Modifier.width(7.dp))
                    Text(text = s + disabledText)
                }
            }
        }
    }
}

fun IntegerTryParse(str: String): Int {
    return try {
        val parsedInt = str.toInt()
        parsedInt
    } catch (nfe: NumberFormatException) {
        0
    }
}

@Composable
fun BigDropdownListIcon(item: String) {
    var painter: Painter

    when(item.lowercase()) {
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
        else -> painter = painterResource(resourcePath = "drawable/sword.png")
    }
    Icon(painter = painter, "", tint = Color.LightGray, modifier = Modifier.size(30.dp).padding(end = 5.dp))
}