package screens.mainscreens

import StatsModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dungeon4dummiesmobile.screens.shared.LogoAsset
import models.CharactersModel
import models.UsersModel
import screens.shared.*
import viewModels.CharactersViewModel
import viewModels.UsersViewModel

@Composable
fun CharacterCreationScreen(
    onBackPressed:() -> Unit,
    onHomeClick:() -> Unit,
    onCharactersClick:() -> Unit,
    onDiceThrowClick:() -> Unit
) {
    val usersViewModel = UsersViewModel()
    val usersModel = UsersModel
    val username = usersModel.currentUser
    val charactersViewModel = CharactersViewModel()

    var racesListSelectedIndex by remember { mutableStateOf(0) }

    var classesListSelectedIndex by remember { mutableStateOf(0) }

    var alignmentsListSelectedIndex by remember { mutableStateOf(0) }

    var user by remember {
        mutableStateOf(usersViewModel.user)
    }
    var character by remember {
        mutableStateOf(value = charactersViewModel.charactersModel)
    }

    val maxPossibleStatLevel = 100

    val races = listOf("Select your race", "Dragonborn", "Dwarf", "Elf", "Gnome", "Half-Elf", "Halfling", "Half-Orc",
        "Human", "Tiefling", "Orc of Exandria", "Leonin", "Satyr", "Fairy", "Harengon", "Owlin",
        "Aarakocra", "Genasi", "Goliath", "Aasimar", "Bugbear", "Firbolg", "Goblin", "Hobgoblin",
        "Kenku", "Kobold", "Lizardfolk", "Orc", "Tabaxi", "Triton", "Yuan-ti Pureblood",
        "Feral Tiefling", "Tortle", "Changeling", "Kalashtar", "Orc of Eberron", "Shifter",
        "Warforged", "Gith", "Centaur", "Loxodon", "Minotaur", "Simic Hybrid", "Vedalken", "Verdan",
        "Locathah", "Grung")

    val classes = listOf("Select your class", "Barbarian", "Bard", "Cleric", "Druid", "Fighter", "Monk", "Paladin", "Rogue",
        "Sorcerer", "Warlock", "Wizard", "Artificer", "Blood Hunter", "Ranger")

    val alignments = listOf("Select your alignment", "Lawful-Good", "Lawful-Neutral", "Lawful-Evil", "Neutral-Good", "True-Neutral",
        "Neutral-Evil", "Chaotic-Good", "Chaotic-Neutral", "Chaotic-Evil")

    var name by remember {
        mutableStateOf("")
    }
    var surname by remember {
        mutableStateOf("")
    }
    var alias by remember {
        mutableStateOf("")
    }
    val status by remember {
        mutableStateOf("Alive")
    }
    var race by remember {
        mutableStateOf(races[racesListSelectedIndex])
    }
    var campaigns by remember {
        mutableStateOf("")
    }
    var characterClass by remember {
        mutableStateOf(classes[classesListSelectedIndex])
    }
    var alignment by remember {
        mutableStateOf(alignments[alignmentsListSelectedIndex])
    }
    var level by remember {
        mutableStateOf(1)
    }
    var exp by remember {
        mutableStateOf(0)
    }
    var age by remember {
        mutableStateOf(20)
    }
    var archetype by remember {
        mutableStateOf("")
    }
    var inventory by remember {
        mutableStateOf(mutableListOf(""))
    }
    var stringInventory by remember {
        mutableStateOf("")
    }

    var attacksSorceries by remember {
        mutableStateOf(mutableListOf(""))
    }
    var stringAttacksSorceries by remember {
        mutableStateOf("")
    }
    var armorClass by remember {
        mutableStateOf(0)
    }
    var initiative by remember {
        mutableStateOf(0)
    }
    var strength by remember {
        mutableStateOf(0)
    }
    var dexterity by remember {
        mutableStateOf(0)
    }
    var constitution by remember {
        mutableStateOf(0)
    }
    var intelligence by remember {
        mutableStateOf(0)
    }
    var wisdom by remember {
        mutableStateOf(0)
    }
    var charisma by remember {
        mutableStateOf(0)
    }
    var acrobatics by remember {
        mutableStateOf(0)
    }
    var athletics by remember {
        mutableStateOf(0)
    }
    var deception by remember {
        mutableStateOf(0)
    }
    var history by remember {
        mutableStateOf(0)
    }
    var insight by remember {
        mutableStateOf(0)
    }
    var intimidation by remember {
        mutableStateOf(0)
    }
    var performance by remember {
        mutableStateOf(0)
    }
    var medicine by remember {
        mutableStateOf(0)
    }
    var nature by remember {
        mutableStateOf(0)
    }
    var perception by remember {
        mutableStateOf(0)
    }
    var persuasion by remember {
        mutableStateOf(0)
    }
    var religion by remember {
        mutableStateOf(0)
    }
    var stealth by remember {
        mutableStateOf(0)
    }
    var survival by remember {
        mutableStateOf(0)
    }
    var animalHandling by remember {
        mutableStateOf(0)
    }
    var getData by remember {
        mutableStateOf(true)
    }
    var maxHP by remember {
        mutableStateOf(0)
    }
    var currentHP by remember {
        mutableStateOf(0)
    }
    var maxMP by remember {
        mutableStateOf(0)
    }
    var currentMP by remember {
        mutableStateOf(0)
    }
    var temporalHP by remember {
        mutableStateOf(0)
    }
    var adventureJournal by remember {
        mutableStateOf(mutableListOf(""))
    }
    var featuresTraits by remember {
        mutableStateOf(mutableListOf(""))
    }
    var stringFeaturesTraits by remember {
        mutableStateOf("")
    }
    var deathSaves by remember {
        mutableStateOf(3)
    }
    var backstory by remember {
        mutableStateOf("")
    }
    var ideals by remember {
        mutableStateOf("")
    }
    var proficiencies by remember {
        mutableStateOf("")
    }
    var flaws by remember {
        mutableStateOf("")
    }
    var personalityTraits by remember {
        mutableStateOf("")
    }
    var bonds by remember {
        mutableStateOf("")
    }
    var languages by remember {
        mutableStateOf("")
    }
    var avatar by remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = getData) {
        if (getData) {
            getData = false
            usersViewModel.get1User(username!!) {
                user = usersViewModel.user
            }
        }
    }

    Scaffold(
        topBar = { TopBar(barText = "Character Creation", onBackPressed = onBackPressed) },
        bottomBar = { BottomBar(onHomeClick = onHomeClick, onCharactersClick = onCharactersClick, onDiceThrowClick = onDiceThrowClick) },
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Card(
                modifier = Modifier.padding(vertical = 50.dp).fillMaxWidth(),
                backgroundColor = MAINCOLOR

            ) {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item { LogoAsset() }

                    item {
                        Button(
                            onClick = {
                                charactersViewModel.genRandomCharacter(username!!)
                                // TOAST

                                onCharactersClick()
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = SECONDARYCOLOR)
                        ) {
                            Icon(painter = painterResource(resourcePath = "drawable/help.png"),
                                contentDescription =  "Create random character icon",
                                modifier = Modifier.size(35.dp))
                            Text(text = "Generate random character",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                    }

                    item { InputTextField("Name", name, onValueChange = {name = it}) }
                    item { InputTextField("Surname", surname, onValueChange = {surname = it}) }
                    item { InputTextField("Alias", alias, onValueChange = {alias = it}) }
                    item { ComposeMenu(
                        list = races,
                        onValueChange = { racesListSelectedIndex = it }
                    )
                    }

                    item { InputTextField("Campaigns", campaigns, onValueChange = {campaigns = it}) }

                    item { ComposeMenu(
                        list = classes,
                        onValueChange = { classesListSelectedIndex = it }
                    )
                    }

                    item { Spacer(modifier = Modifier.height(15.dp)) }

                    item { ComposeMenu(
                        list = alignments,
                        onValueChange = { alignmentsListSelectedIndex = it }
                    )
                    }
                    item { Row {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            NumericInput(label = "Level", number = level, onValueChange = {level = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            NumericInput(label = "Exp", number = exp, onValueChange = {exp = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            NumericInput(label = "Age", number = age, onValueChange = {age = it})
                        }
                    } }
                    item { InputTextField("Archetype", archetype, onValueChange = {archetype = it}) }
                    item { LongInputTextField(label = "Inventory (separate items with [ ; ])", inValue = stringInventory, onValueChange = {stringInventory = it})}


                    item { Text(text = "Stats", modifier = Modifier.padding(15.dp), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 20.sp)}

                    item { Text(text = "Main Stats", modifier = Modifier.padding(15.dp), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 15.sp)}


                    item { Row {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(resourcePath = "drawable/shield.png"), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Armor Class", number = armorClass, onValueChange = {armorClass = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(resourcePath = "drawable/initiative.png"), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Initiative", number = initiative, onValueChange = {initiative = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(resourcePath = "drawable/strength.png"), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Strength", number = strength, onValueChange = {strength = it})
                        }
                    } }
                    item { Row {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(resourcePath = "drawable/karate.png"), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Dexterity", number = dexterity, onValueChange = {dexterity = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(resourcePath = "drawable/heart.png"), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Constitution", number = constitution, onValueChange = {constitution = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(resourcePath = "drawable/idea.png"), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Intelligence", number = intelligence, onValueChange = {intelligence = it})
                        }
                    } }

                    item { Text(text = "Secondary Stats", modifier = Modifier.padding(15.dp), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 15.sp)}


                    item { Row {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(resourcePath = "drawable/brain.png"), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Wisdom", number = wisdom, onValueChange = {wisdom = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(resourcePath = "drawable/headcheck.png"), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Charisma", number = charisma, onValueChange = {charisma = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(resourcePath = "drawable/acrobatics.png"), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Acrobatics", number = acrobatics, onValueChange = {acrobatics = it})
                        }
                    } }
                    item { Row {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(resourcePath = "drawable/run.png"), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Athletics", number = athletics, onValueChange = {athletics = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(resourcePath = "drawable/mask.png"), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Deception", number = deception, onValueChange = {deception = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(resourcePath = "drawable/script.png"), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "History", number = history, onValueChange = {history = it})
                        }
                    } }
                    item { Row {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(resourcePath = "drawable/flask.png"), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Insight", number = insight, onValueChange = {insight = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(resourcePath = "drawable/angry.png"), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Intimidation", number = intimidation, onValueChange = {intimidation = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(resourcePath = "drawable/fawkes.png"), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Performance", number = performance, onValueChange = {performance = it})
                        }
                    } }
                    item { Row {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(resourcePath = "drawable/medic.png"), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Medicine", number = medicine, onValueChange = {medicine = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(resourcePath = "drawable/sprout.png"), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Nature", number = nature, onValueChange = {nature = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(resourcePath = "drawable/eye.png"), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Perception", number = perception, onValueChange = {perception = it})
                        }
                    } }
                    item { Row {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(resourcePath = "drawable/persuasion.png"), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Persuasion", number = persuasion, onValueChange = {persuasion = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(resourcePath = "drawable/cross.png"), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Religion", number = religion, onValueChange = {religion = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(resourcePath = "drawable/stealth.png"), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Stealth", number = stealth, onValueChange = {stealth = it})
                        }
                    } }
                    item { Row {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(resourcePath = "drawable/survival.png"), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Survival", number = survival, onValueChange = {survival = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(resourcePath = "drawable/dog.png"), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Animal Handling", number = animalHandling, onValueChange = {animalHandling = it})
                        }
                    } }
                    item { Row {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(resourcePath = "drawable/heart.png"), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.Red)
                            NumericInput(label = "HP", number = maxHP, onValueChange = {
                                maxHP = it
                                currentHP = it
                            })
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(resourcePath = "drawable/flask.png"), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.Blue)
                            NumericInput(label = "MP", number = maxMP, onValueChange = {
                                maxMP = it
                                currentMP = it
                            })
                        }
                    } }

                    item { LongInputTextField(label = "Features & Traits (separate items with [ ; ])", inValue = stringFeaturesTraits, onValueChange = {stringFeaturesTraits = it})}
                    item { LongInputTextField(label = "Attacks & Sorceries (separate items with [ ; ])", inValue = stringAttacksSorceries, onValueChange = {stringAttacksSorceries = it})}
                    item { LongInputTextField(label = "Backstory", inValue = backstory, onValueChange = {backstory = it})}
                    item { LongInputTextField(label = "Ideals", inValue = ideals, onValueChange = {ideals = it})}
                    item { LongInputTextField(label = "Proficiencies", inValue = proficiencies, onValueChange = {proficiencies = it})}
                    item { LongInputTextField(label = "Flaws", inValue = flaws, onValueChange = {flaws = it})}
                    item { LongInputTextField(label = "Personality Traits", inValue = personalityTraits, onValueChange = {personalityTraits = it})}
                    item { LongInputTextField(label = "Bonds", inValue = bonds, onValueChange = {bonds = it})}
                    item { LongInputTextField(label = "Languages", inValue = languages, onValueChange = {languages = it})}
                    item { LongInputTextField(label = "Avatar (direct link)", inValue = avatar, onValueChange = {avatar = it})}

                    ///// END /////

                    item { Spacer(modifier = Modifier.height(50.dp)) }

                    item { Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = SECONDARYCOLOR),
                        onClick = {
                            inventory = trimStringArray(stringInventory.replace("\n", "").removeSuffix(";").split(";").toMutableList())
                            attacksSorceries = trimStringArray(stringAttacksSorceries.replace("\n", "").removeSuffix(";").split(";").toMutableList())
                            featuresTraits = trimStringArray(stringFeaturesTraits.replace("\n", "").removeSuffix(";").split(";").toMutableList())

                            //// INICIO COMPROBACIONES ////

                            if (name == "" || name == " ") {
                                return@Button
                            } else if (race == "Select your race") {
                                return@Button
                            } else if (characterClass == "Select your class") {
                                return@Button
                            } else if (alignment == "Select your alignment") {
                                return@Button
                            } else if (level < 1 || level >= maxPossibleStatLevel) {
                                return@Button
                            } else if (exp < 0 || exp >= 500000) {
                                return@Button
                            } else if (age < 0 || age >= 500000) {
                                return@Button
                            } else if (armorClass >= maxPossibleStatLevel) {
                                return@Button
                            } else if (initiative >= maxPossibleStatLevel) {
                                return@Button
                            } else if (strength >= maxPossibleStatLevel) {
                                return@Button
                            } else if (dexterity >= maxPossibleStatLevel) {
                                return@Button
                            } else if (constitution >= maxPossibleStatLevel) {
                                return@Button
                            } else if (intelligence >= maxPossibleStatLevel) {
                                return@Button
                            } else if (wisdom >= maxPossibleStatLevel) {
                                return@Button
                            } else if (charisma >= maxPossibleStatLevel) {
                                return@Button
                            } else if (acrobatics >= maxPossibleStatLevel) {
                                return@Button
                            } else if (athletics >= maxPossibleStatLevel) {
                                return@Button
                            } else if (deception >= maxPossibleStatLevel) {
                                return@Button
                            } else if (history >= maxPossibleStatLevel) {
                                return@Button
                            } else if (insight >= maxPossibleStatLevel) {
                                return@Button
                            } else if (intimidation >= maxPossibleStatLevel) {
                                return@Button
                            } else if (performance >= maxPossibleStatLevel) {
                                return@Button
                            } else if (medicine >= maxPossibleStatLevel) {
                                return@Button
                            } else if (nature >= maxPossibleStatLevel) {
                                return@Button
                            } else if (perception >= maxPossibleStatLevel) {
                                return@Button
                            } else if (persuasion >= maxPossibleStatLevel) {
                                return@Button
                            } else if (religion >= maxPossibleStatLevel) {
                                return@Button
                            } else if (stealth >= maxPossibleStatLevel) {
                                return@Button
                            } else if (survival >= maxPossibleStatLevel) {
                                return@Button
                            } else if (animalHandling >= maxPossibleStatLevel) {
                                return@Button
                            } else if (maxHP < 0 || maxHP >= 500) {
                                return@Button
                            } else if (maxMP < 0 || maxMP >= 500) {
                                return@Button
                            }

                            character = CharactersModel(
                                _id = "",
                                id = "",
                                name = name,
                                surname = surname,
                                alias = alias,
                                character_class = characterClass,
                                status = status,
                                race = race,
                                campaigns = campaigns,
                                alignment = alignment,
                                level = level,
                                exp = exp,
                                archetype = archetype,
                                inventory = inventory,
                                max_hp = maxHP,
                                current_hp = currentHP,
                                temporal_hp = temporalHP,
                                max_mana = maxMP,
                                current_mana = currentMP,
                                adventure_journal = adventureJournal,
                                features_traits = featuresTraits,
                                death_saves = deathSaves,
                                backstory = backstory,
                                ideals = ideals,
                                proficiencies = proficiencies,
                                flaws = flaws,
                                personality_traits = personalityTraits,
                                bonds = bonds,
                                age = age,
                                avatar = avatar,
                                languages = languages,
                                attacks_sorceries = attacksSorceries,
                                owner = user.username,

                                stats = StatsModel(
                                    ArmorClass = armorClass,
                                    Initiative = initiative,
                                    Strength = strength,
                                    Dexterity = dexterity,
                                    Constitution = constitution,
                                    Intelligence = intelligence,
                                    Wisdom = wisdom,
                                    Charisma = charisma,
                                    Acrobatics = acrobatics,
                                    Athletics = athletics,
                                    Deception = deception,
                                    History = history,
                                    Insight = insight,
                                    Intimidation = intimidation,
                                    Performance = performance,
                                    Medicine = medicine,
                                    Nature = nature,
                                    Perception = perception,
                                    Persuasion = persuasion,
                                    Religion = religion,
                                    Stealth = stealth,
                                    Survival = survival,
                                    AnimalHandling = animalHandling
                                )
                            )

                            // POST

                            charactersViewModel.postCharacter(character)

                            onCharactersClick

                        }) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painter = painterResource(resourcePath = "drawable/d20.png"), "", tint = Color.Cyan,
                                modifier = Modifier.height(50.dp).width(50.dp))
                            Text(text = "Create Character")
                        }
                    }
                    }

                    item { Spacer(modifier = Modifier.height(50.dp)) }
                }
            }
        }
    }
}


fun trimStringArray(array: MutableList<String>): MutableList<String> {
    return array.map{it.trim()}.toMutableList()
}