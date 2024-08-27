package com.example.fitnessapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.fitnessapp.Data.MyViewModel
import com.example.fitnessapp.Data.MyViewModel2
import com.example.fitnessapp.Screens.ExerciseScreen
import com.example.fitnessapp.Screens.MainScreen
import com.example.fitnessapp.Screens.SplashScreen

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel = ViewModelProvider(this)[MyViewModel::class.java]
            val viewModel2 = ViewModelProvider(this)[MyViewModel2::class.java]
            NavHost(navController = navController, startDestination = SplashScreen) {
                composable<SplashScreen> { SplashScreen(navController) }
                composable<MainScreen> { MainScreen(navController,viewModel,viewModel2) }
                composable<ExerciseScreen> {
                    val data = it.toRoute<ExerciseScreen>()
                    ExerciseScreen(viewModel2,data.id,navController)  }
            }
        }
    }
}
