package com.simplehabit.tracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class Habit(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String = "",
    val targetDays: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)
