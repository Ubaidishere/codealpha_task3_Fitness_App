# Fitness Tracking App

## Overview

The **Fitness Tracking App** is a gym tracking application designed to help users manage their workouts and exercises efficiently. It allows users to create workouts, add exercises to those workouts, track the progress of exercises, and manage their fitness routines. The app is built using Android's Jetpack libraries, Room for local database management, and Jetpack Compose for a modern UI experience.

## Features

- **Workout Management**: Create and delete workout sessions.
- **Exercise Tracking**: Add, edit, and manage exercises within a workout.
- **Progress Tracking**: Mark exercises as completed and view overall progress through a progress bar.
- **User Interface**: Modern and intuitive UI using Jetpack Compose.
- **Data Persistence**: Local data storage using Room Database with TypeConverters for complex data types.

## Screenshots

![Main Screen](![Screenshot_20240827-234443](https://github.com/user-attachments/assets/e017d164-c47f-43bb-9ea7-c554100e3f09)
)
![Exercise Screen](![Screenshot_20240827-234408](https://github.com/user-attachments/assets/366e23a1-4d90-4a19-ba5f-ba9a3ea0af8b)
)

## Technologies Used

- **Kotlin**: Programming language for Android development.
- **Jetpack Compose**: Modern toolkit for building native UI.
- **Room Database**: SQLite object-mapping library for local storage.
- **Coroutines**: For asynchronous programming.
- **Gson**: For JSON serialization and deserialization.

## Architecture

The app follows a clean architecture pattern with the following components:

- **Data Layer**: Contains Room database entities, DAOs, and TypeConverters.
- **Domain Layer**: ViewModel for managing UI-related data and business logic.
- **Presentation Layer**: Composable functions for the app’s UI.
