package com.simplehabit.tracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.simplehabit.tracker.ui.components.HabitItem
import com.simplehabit.tracker.viewmodel.HabitViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HabitViewModel) {
    val habits by viewModel.habits.collectAsStateWithLifecycle(initialValue = emptyList())
    val todayDate = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.timeInMillis

    Scaffold(
        topBar = { TopAppBar(title = { Text("Simple Habit Tracker") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* TODO: 添加习惯 */ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(habits) { habit ->
                HabitItem(
                    habit = habit,
                    onToggle = { viewModel.toggleHabitForToday(habit.id, todayDate) }
                )
            }
        }
    }
}
