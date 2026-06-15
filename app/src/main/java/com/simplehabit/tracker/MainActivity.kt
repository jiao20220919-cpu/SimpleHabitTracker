package com.simplehabit.tracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.simplehabit.tracker.data.HabitDatabase
import com.simplehabit.tracker.repository.HabitRepository
import com.simplehabit.tracker.ui.screens.HomeScreen
import com.simplehabit.tracker.ui.theme.SimpleHabitTrackerTheme
import com.simplehabit.tracker.viewmodel.HabitViewModel
import com.simplehabit.tracker.viewmodel.HabitViewModelFactory

class MainActivity : ComponentActivity() {
    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            HabitDatabase::class.java,
            "habit_database"
        ).build()
    }
    private val repository by lazy { HabitRepository(database.habitDao(), database.habitLogDao()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleHabitTrackerTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val viewModel: HabitViewModel = viewModel(factory = HabitViewModelFactory(repository))
                    HomeScreen(viewModel)
                }
            }
        }
    }
}
