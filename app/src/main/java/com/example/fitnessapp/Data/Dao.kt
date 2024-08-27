package com.example.fitnessapp.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface exerciseDao {
    @Upsert
    fun insertExercise(exercise: Exercise)
    @Query("SELECT * FROM Exercise WHERE workoutId = :workoutId")
    fun getExercisesForWorkout(workoutId: Int): LiveData<List<Exercise>>

    @Query("SELECT COUNT(*) FROM Exercise WHERE `check` = 1")
    fun getCountOfCheckedExercises(): LiveData<Int>

    @Query("SELECT * FROM Exercise")
    fun getAllExercises(): LiveData<List<Exercise>>

}

@Dao
interface Dao {

    @Query("SELECT * FROM Workout")
    fun getWorkout():LiveData<List<Workout>>

    @Query("SELECT * FROM Workout WHERE id = :id")
    fun getWorkoutById(id: Int): LiveData<Workout>

    @Upsert
    fun addWorkout(workout: Workout)

    @Query("DELETE FROM Workout WHERE id = :id")
    fun deleteWorkout(id: Int)


}