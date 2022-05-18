package com.example.dungeon4dummiesmobile.screens.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun LogoAsset() {
    Image(
        painterResource(resourcePath = "drawable/dungeon4dummieslogo.png"),
        "Dungeon4Dummies Logo",
        modifier = Modifier.height(110.dp).width(170.dp)
    )
}