package com.example.fitnessapp.Data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    private val _getData = MyApplication.myDatabase.getWorkoutDao()
    val getData: LiveData<List<Workout>> = _getData.getWorkout()

    fun deleteWorkout(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _getData.deleteWorkout(id)
        }
    }


    fun addWorkout(workout: Workout) {
        viewModelScope.launch(Dispatchers.IO) {
            _getData.addWorkout(workout)
        }
    }
}
