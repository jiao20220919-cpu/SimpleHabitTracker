package com.simplehabit.tracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitLogDao {
    @Query("SELECT * FROM habit_logs WHERE habitId = :habitId AND date >= :startDate ORDER BY date ASC")
    fun getLogsForHabit(habitId: Long, startDate: Long): Flow<List<HabitLog>>

    @Insert
    suspend fun insert(log: HabitLog)

    @Query("DELETE FROM habit_logs WHERE habitId = :habitId AND date = :date")
    suspend fun deleteLog(habitId: Long, date: Long)

    @Query("SELECT EXISTS(SELECT 1 FROM habit_logs WHERE habitId = :habitId AND date = :date AND completed = 1)")
    suspend fun isCompleted(habitId: Long, date: Long): Boolean
}
