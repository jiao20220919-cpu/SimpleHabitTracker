package com.simplehabit.tracker.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Habit::class, HabitLog::class], version = 1, exportSchema = false)
abstract class HabitDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
    abstract fun habitLogDao(): HabitLogDao
}
