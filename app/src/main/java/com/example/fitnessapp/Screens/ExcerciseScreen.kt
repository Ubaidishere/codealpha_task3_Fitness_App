package com.example.fitnessapp.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fitnessapp.Data.Exercise
import com.example.fitnessapp.Data.MyViewModel2
import com.example.fitnessapp.R
import com.example.fitnessapp.ui.theme.DarkBlue
import com.example.fitnessapp.ui.theme.ProgressEnd
import kotlinx.serialization.Serializable

@Serializable
data class ExerciseScreen(
    val id :Int,
)

@Composable
fun ExerciseScreen(viewModel: MyViewModel2, id: Int,navController: NavController) {
    val data by viewModel.getExercisesForWorkout(id).observeAsState(emptyList())
    var name by remember { mutableStateOf("") }
    var sets by remember { mutableStateOf("") }
    var reps by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var check1 by remember { mutableStateOf(false) }
    var check2 by remember { mutableStateOf(false) }
    var check3 by remember { mutableStateOf(false) }
    var check4 by remember { mutableStateOf(false) }
    var currentExerciseId by remember { mutableStateOf<Int?>(null) }



    if (showDialog){
        AlertDialog(
            containerColor = Color.White,
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Add Exercise", color = Color.Black) },
            text = {
                Column {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
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
                    Row {
                        OutlinedTextField(
                            value = duration,
                            onValueChange = {duration = it },
                            Modifier
                                .padding(top = 4.dp)
                                .weight(1f),
                            singleLine = true,
                            label = {
                                Text(text = "Time")
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Blue,
                                unfocusedBorderColor = Color.Blue,
                                unfocusedTextColor = Color.Black,
                                focusedTextColor = Color.Black,
                                cursorColor = Color.Blue,
                                errorTextColor = Color.Red
                            ),
                            isError = check2
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedTextField(
                            value = sets,
                            onValueChange = { sets = it },
                            Modifier
                                .padding(top = 4.dp)
                                .weight(1f),
                            singleLine = true,
                            label = {
                                Text(text = "Sets")
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Blue,
                                unfocusedBorderColor = Color.Blue,
                                unfocusedTextColor = Color.Black,
                                focusedTextColor = Color.Black,
                                cursorColor = Color.Blue,
                                errorTextColor = Color.Red
                            ),
                            isError = check3
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedTextField(
                            value = reps,
                            onValueChange = { reps = it },
                            Modifier
                                .padding(top = 4.dp)
                                .weight(1f),
                            singleLine = true,
                            label = {
                                Text(text = "Reps")
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Blue,
                                unfocusedBorderColor = Color.Blue,
                                unfocusedTextColor = Color.Black,
                                focusedTextColor = Color.Black,
                                cursorColor = Color.Blue,
                                errorTextColor = Color.Red
                            ),
                            isError = check4
                        )
                    }
                }
            },
            confirmButton = {
                Button(onClick = {
                    if (name == ""){
                        check1 = true
                    }
                    else if (duration == ""){
                        check2 = true
                    }
                    else if (sets == ""){
                        check3 = true
                    }
                    else if (reps == ""){
                        check4 = true
                    }
                    else{
                        val newExercise = Exercise(
                            id = currentExerciseId ?: 0,
                            name = name,
                            check = false,
                            duration = duration.toInt(),
                            reps = reps.toInt(),
                            sets = sets.toInt(),
                            workoutId = id
                        )
                        viewModel.insertExercise(newExercise)
                        name = ""
                        sets = ""
                        reps = ""
                        duration = ""
                        check1 = false
                        check2 = false
                        check3 = false
                        check4 = false
                        showDialog = false
                    }
                },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (check1) Color.Red else DarkBlue,
                        contentColor =  Color.White
                    )){
                    Text(text = "Submit")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        check1 = false
                        check2 = false
                        check3 = false
                        check4 = false
                        name = ""
                        sets = ""
                        reps = ""
                        duration = ""
                        showDialog = false
                    },colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    )){
                    Text(text = "Cancel")
                }
            }
        )

    }

    Scaffold(
        containerColor = Color.Black,
        bottomBar = { AddButton("Create new Exercise", onClick = {
            showDialog = !showDialog
        }) }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.Black)
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "", tint = Color.White,
                modifier = Modifier.size(60.dp).padding(start = 20.dp, top = 25.dp).clickable { navController.popBackStack()  })
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
                            .padding(top = 20.dp, bottom = 20.dp, end = 10.dp)
                            .size(50.dp)
                    )
                    Text(
                        text = "Exercises",
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                HorizontalDivider()
                if(data.isEmpty()){
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        Text(text = "Add Exercise", color = Color.White)
                    }
                }else {
                    LazyColumn {
                        itemsIndexed(data) { _, exercise ->
                            Exercises(exercise, onClick = {
                                viewModel.insertExercise(exercise.copy(check = !exercise.check))
                            }, edit = {
                                showDialog = true
                                currentExerciseId = exercise.id
                                name = exercise.name
                                sets = exercise.sets.toString()
                                reps = exercise.reps.toString()
                                duration = exercise.duration.toString()
                            })
                        }
                    }
                }
            }
        }
    }}

@Composable
fun Exercises(exercise: Exercise,onClick: () -> Unit,edit: () -> Unit ){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)
        .background(ProgressEnd)){
        Box(modifier = Modifier
            .fillMaxSize().clickable { edit() }
            .padding(20.dp)){
            Box(modifier = Modifier
                .align(Alignment.CenterEnd)
            ){
                Checkbox(colors = CheckboxDefaults.colors(Color.Black),checked = exercise.check, onCheckedChange = {onClick()})
            }
            Column {
                Text(text = exercise.name,color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Row {
                    Column {
                        Text(text = "Duration:",color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 5.dp))
                        Text(text = "${exercise.duration}",color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold,modifier = Modifier.padding(top = 5.dp))
                    }
                    Column {
                        Text(text = "Reps:",color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold,modifier = Modifier.padding(top = 5.dp, start = 15.dp))
                        Text(text = "${exercise.reps}",color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold,modifier = Modifier.padding(top = 5.dp, start = 15.dp))
                    }
                    Column {
                        Text(text = "Sets:",color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold,modifier = Modifier.padding(top = 5.dp, start = 15.dp))
                        Text(text = "${exercise.sets}",color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold,modifier = Modifier.padding(top = 5.dp, start = 15.dp))
                    }

                }
            }
        }
    }
}



