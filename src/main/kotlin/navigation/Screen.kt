package navigation

import dev.icerock.moko.parcelize.Parcelable
import dev.icerock.moko.parcelize.Parcelize

sealed class Screen: Parcelable {

    @Parcelize
    object LoginScreen: Screen()

    @Parcelize
    object RegisterScreen: Screen()

    @Parcelize
    object HomeScreen: Screen()

    @Parcelize
    object CharactersScreen: Screen()

    @Parcelize
    object CharacterScreen: Screen()

    @Parcelize
    object ProfileScreen: Screen()
}