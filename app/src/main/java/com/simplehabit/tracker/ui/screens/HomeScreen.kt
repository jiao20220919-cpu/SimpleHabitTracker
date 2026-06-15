package com.simplehabit.tracker.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.simplehabit.tracker.ui.components.HabitItem
import com.simplehabit.tracker.viewmodel.HabitViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HabitViewModel) {
    val habits by viewModel.habits.collectAsStateWithLifecycle(initialValue = emptyList())
    val context = LocalContext.current
    val today = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.timeInMillis

    var showAddDialog by remember { mutableStateOf(false) }
    var habitName by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Simple Habit Tracker") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Habit")
            }
        }
    ) { paddingValues ->
        if (habits.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("No habits yet.\nTap + to add your first habit.")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(habits) { habit ->
                    HabitItem(
                        habit = habit,
                        onToggle = {
                            viewModel.toggleHabitForToday(habit.id, today)
                            Toast.makeText(context, "Habit updated", Toast.LENGTH_SHORT).show()
                        },
                        onDelete = {
                            viewModel.deleteHabit(habit.id)
                            Toast.makeText(context, "Habit deleted", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }

    if (showAddDialog) {
        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = { Text("Add Habit") },
            text = {
                OutlinedTextField(
                    value = habitName,
                    onValueChange = { habitName = it },
                    label = { Text("Habit name") },
                    singleLine = true
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (habitName.isNotBlank()) {
                            viewModel.addHabit(com.simplehabit.tracker.data.Habit(name = habitName))
                            habitName = ""
                            showAddDialog = false
                        }
                    }
                ) {
                    Text("Add")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}
