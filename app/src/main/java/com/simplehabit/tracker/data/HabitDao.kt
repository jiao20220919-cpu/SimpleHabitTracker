package com.simplehabit.tracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Query("SELECT * FROM habits ORDER BY createdAt ASC")
    fun getAllHabits(): Flow<List<Habit>>

    @Insert
    suspend fun insert(habit: Habit): Long

    @Query("DELETE FROM habits WHERE id = :habitId")
    suspend fun delete(habitId: Long)
}
