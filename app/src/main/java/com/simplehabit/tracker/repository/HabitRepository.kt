package com.simplehabit.tracker.repository

import com.simplehabit.tracker.data.Habit
import com.simplehabit.tracker.data.HabitDao
import com.simplehabit.tracker.data.HabitLog
import com.simplehabit.tracker.data.HabitLogDao
import kotlinx.coroutines.flow.Flow

class HabitRepository(
    private val habitDao: HabitDao,
    private val habitLogDao: HabitLogDao
) {
    fun getAllHabits(): Flow<List<Habit>> = habitDao.getAllHabits()

    suspend fun addHabit(habit: Habit) = habitDao.insert(habit)

    suspend fun updateHabit(habit: Habit) = habitDao.update(habit)

    suspend fun deleteHabit(habitId: Long) = habitDao.delete(habitId)

    suspend fun toggleHabitForToday(habitId: Long, date: Long) {
        val exists = habitLogDao.isCompleted(habitId, date)
        if (exists) {
            habitLogDao.deleteLog(habitId, date)
        } else {
            habitLogDao.insert(HabitLog(habitId = habitId, date = date, completed = true))
        }
    }

    fun getLogsForHabit(habitId: Long, startDate: Long) = habitLogDao.getLogsForHabit(habitId, startDate)
}
