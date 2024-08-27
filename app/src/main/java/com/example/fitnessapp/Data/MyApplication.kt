package com.example.fitnessapp.Data

import android.app.Application
import androidx.room.Room

class MyApplication:Application() {

    companion object {
        lateinit var myDatabase : MyDatabase
        lateinit var exerciseDao: MyDatabase
    }

    override fun onCreate() {
        super.onCreate()

        exerciseDao = Room.databaseBuilder(
            applicationContext,
            MyDatabase::class.java,
            "database"
        ).build()

        myDatabase = Room.databaseBuilder(
            applicationContext,
            MyDatabase::class.java,
            "database"
        ).build()
    }
}