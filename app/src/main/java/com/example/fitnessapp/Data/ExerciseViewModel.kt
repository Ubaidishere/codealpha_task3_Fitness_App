package com.example.fitnessapp.Data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MyViewModel2 : ViewModel() {

    private val _getData = MyApplication.exerciseDao.getExerciseDao()
    val getData: LiveData<List<Exercise>> = _getData.getAllExercises()

    init {
        _getData.getAllExercises()
    }

    fun insertExercise(exercise: Exercise) {
        viewModelScope.launch(Dispatchers.IO) {
            _getData.insertExercise(exercise)
        }
    }

    fun getExercisesForWorkout(workoutId: Int): LiveData<List<Exercise>> {
        return _getData.getExercisesForWorkout(workoutId)
    }

    fun getCountOfCheckedExercises(): LiveData<Int> {
            return _getData.getCountOfCheckedExercises()
    }
}