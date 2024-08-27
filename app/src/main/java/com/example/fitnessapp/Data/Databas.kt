package com.example.fitnessapp.Data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Workout::class, Exercise::class], version = 2)
@TypeConverters(Converters::class)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getWorkoutDao(): Dao
    abstract fun getExerciseDao(): exerciseDao
}
