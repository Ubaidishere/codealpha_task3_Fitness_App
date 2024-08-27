package com.example.fitnessapp.Screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fitnessapp.Data.MyViewModel
import com.example.fitnessapp.Data.MyViewModel2
import com.example.fitnessapp.Data.Workout
import com.example.fitnessapp.R
import com.example.fitnessapp.ui.theme.DarkBlue
import com.example.fitnessapp.ui.theme.ProgressBar
import com.example.fitnessapp.ui.theme.ProgressEnd
import kotlinx.serialization.Serializable


@Serializable
object MainScreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(navController: NavController, viewModel: MyViewModel, viewModel2: MyViewModel2) {

    val exercise by viewModel.getData.observeAsState(emptyList())
    var workout by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var check1 by remember { mutableStateOf(false) }
    val progress by viewModel2.getCountOfCheckedExercises().observeAsState(0)
    var showBoll by remember { mutableStateOf(false) }
        if (showBoll) {
            AlertDialog(
                onDismissRequest = { showBoll = false },
                title = {
                    Text(text = "Item Deleted")
                },
                text = {
                    Text("Your item has been successfully deleted.")
                },
                confirmButton = {
                    TextButton(
                        onClick = {showBoll = false}
                    ) {
                        Text("OK")
                    }
                }
            )
        }
    val bar = remember(progress) {
        when (progress) {
            5 -> 1f
            4 -> 0.75f
            3 -> 0.5f
            2 -> 0.25f
            1 -> 0.1f
            0 -> 0f
            else -> 1f
        }
    }



    if (showDialog) {
        AlertDialog(
            containerColor = Color.White,
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Add Workout", color = Color.Black) },
            text = {
                Column {
                    OutlinedTextField(
                        value = workout,
                        onValueChange = { workout = it },
                        Modifier.padding(top = 4.dp),
                        singleLine = true,
                        label = {
                            Text(text = "Workout Name")
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Blue,
                            unfocusedBorderColor = Color.Blue,
                            unfocusedTextColor = Color.Black,
                            focusedTextColor = Color.Black,
                            cursorColor = Color.Blue,
                            errorTextColor = Color.Red
                        ),
                        isError = check1
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (workout.isEmpty()) {
                            check1 = true
                        } else {
                            viewModel.addWorkout(Workout(name = workout))
                            workout = ""
                            check1 = false
                            showDialog = false
                        }
                    },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (check1) Color.Red else DarkBlue,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Submit")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        check1 = false
                        workout = ""
                        showDialog = false
                    },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Cancel")
                }
            }
        )
    }

    Scaffold(
        containerColor = Color.Black,
        bottomBar = {
            AddButton("Create new workout", onClick = {
                showDialog = !showDialog
            })
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.Black)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(20.dp)
                            .size(50.dp)
                    )
                    Text(
                        text = "Fitness Tracking App",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                HorizontalDivider()
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .background(DarkBlue)
                        .height(100.dp)
                ) {
                    Column(Modifier.padding(20.dp)) {
                        Text(text = "Welcome back,", color = Color.White, fontSize = 20.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Sir!", color = Color.White, fontSize = 20.sp)
                    }
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 10.dp, end = 15.dp),
                        painter = painterResource(id = R.drawable.pic),
                        contentDescription = "",
                        alignment = Alignment.BottomEnd
                    )
                }
                ProgressBar(bar)

                Text(
                    color = Color.White,
                    fontSize = 20.sp,
                    text = "Choose Workouts:",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                )

                if (exercise.isEmpty()) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Add Workout", color = Color.White)
                    }
                } else {
                    LazyColumn {
                        itemsIndexed(exercise) { _, data ->
                            Workouts(navController, data.name, data.id, onClick = {
                                viewModel.deleteWorkout(data.id)
                                showBoll = true
                            })
                        }
                    }
                }
            }
        }
    }
}





@Composable
fun Workouts(navController:NavController,name:String,id:Int,onClick: () -> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable { navController.navigate(ExerciseScreen(id)) }
        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
        .background(
            ProgressEnd
        )){
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)){
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Row {
                    Icon(Icons.Default.Delete, contentDescription = "", tint = Color.Red, modifier = Modifier.clickable { onClick()  })
                    VerticalDivider()
                    Text(text = name, color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 10.dp))
                }
                Icon(Icons.Default.PlayArrow, contentDescription = "")
            }
        }
    }
}

@Composable
fun AddButton(text:String,onClick: () -> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .padding(20.dp)
        .clip(shape = RoundedCornerShape(10.dp))
        .background(DarkBlue), contentAlignment = Alignment.Center) {
        Box(modifier = Modifier.padding(20.dp)){
            Row {
                Icon(Icons.Default.Add, contentDescription = "", tint = Color.White)
                Text(text = text, color = Color.White, fontSize = 20.sp, modifier = Modifier.padding(start = 10.dp))
            }
        }
    }
}
