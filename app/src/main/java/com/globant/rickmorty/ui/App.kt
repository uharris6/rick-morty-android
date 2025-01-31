package com.globant.rickmorty.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Start) {
        composable<Start> {
            Start()
        }
    }
}

@Composable
fun Start() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Welcome", modifier = Modifier.align(Alignment.Center))
    }
}

@Serializable
object Start