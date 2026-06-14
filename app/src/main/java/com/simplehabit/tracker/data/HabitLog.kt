package com.simplehabit.tracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habit_logs")
data class HabitLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val habitId: Long,
    val date: Long,
    val completed: Boolean = true
)
