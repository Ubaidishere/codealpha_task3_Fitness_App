package com.example.fitnessapp.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fitnessapp.R
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable

@Serializable
object SplashScreen

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(MainScreen)
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
        .padding(bottom = 20.dp),
        contentAlignment = Alignment.Center){
        Column {
            Icon(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo", tint = Color.White, modifier = Modifier
                .align(
                    Alignment.CenterHorizontally
                )
                .padding(20.dp))
            Text(text = "Welcome to Fitness Tracking App", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}